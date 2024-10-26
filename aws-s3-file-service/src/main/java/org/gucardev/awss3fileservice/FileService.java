package org.gucardev.awss3fileservice;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${aws.bucket}")
    private String bucketName;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final WatermarkService watermarkService;

    /**
     * Generates a presigned URL for GET or PUT operations with specified access type.
     */
    public String generatePreSignedUrl(String filePath, SdkHttpMethod method, AccessType accessType) {
        if (method == SdkHttpMethod.GET) {
            return generateGetPresignedUrl(filePath);
        } else if (method == SdkHttpMethod.PUT) {
            return generatePutPresignedUrl(filePath, accessType);
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
    }

    /**
     * Generates a presigned GET URL.
     */
    private String generateGetPresignedUrl(String filePath) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(60))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }

    /**
     * Generates a presigned PUT URL with optional ACL based on AccessType.
     */
    private String generatePutPresignedUrl(String filePath, AccessType accessType) {
        PutObjectRequest.Builder putObjectRequestBuilder = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath);

        if (accessType == AccessType.PUBLIC) {
            putObjectRequestBuilder.acl(ObjectCannedACL.PUBLIC_READ);
        }

        PutObjectRequest putObjectRequest = putObjectRequestBuilder.build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(60))
                .putObjectRequest(putObjectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }

    /**
     * Generates multiple presigned URLs with specified access type.
     */
    public List<MultiplePreSignedUrlResponse> generateMultiplePreSignedUrls(List<MultiplePreSignedUrlRequest> requests, AccessType accessType) {
        List<MultiplePreSignedUrlResponse> urls = new ArrayList<>();
        for (MultiplePreSignedUrlRequest request : requests) {
            String filename = buildFilename(request.getOriginalFileName());
            String url = generatePreSignedUrl(filename, SdkHttpMethod.PUT, accessType);
            MultiplePreSignedUrlResponse response = new MultiplePreSignedUrlResponse(url, filename, request.getOriginalFileName());
            urls.add(response);
        }
        return urls;
    }

    /**
     * Builds a sanitized filename with a timestamp prefix.
     */
    public static String buildFilename(String filename) {
        return String.format("%s_%s", System.currentTimeMillis(), sanitizeFileName(filename));
    }

    /**
     * Uploads a MultipartFile to S3 with specified access type.
     */
    public String uploadMultipartFile(MultipartFile file, AccessType accessType) throws IOException {
        String fileName = buildFilename(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest.Builder putObjectRequestBuilder = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName);

            if (accessType == AccessType.PUBLIC) {
                putObjectRequestBuilder.acl(ObjectCannedACL.PUBLIC_READ);
            }

            PutObjectRequest putObjectRequest = putObjectRequestBuilder.build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
        }
        return fileName;
    }

    /**
     * Downloads a file from S3 and returns an InputStream and ETag.
     */
    public S3ObjectInputStreamWrapper downloadFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseInputStream<GetObjectResponse> s3ObjectResponse = s3Client.getObject(getObjectRequest);
        String eTag = s3ObjectResponse.response().eTag();
        return new S3ObjectInputStreamWrapper(s3ObjectResponse, eTag);
    }

    /**
     * Downloads a file from S3, adds a watermark, and returns an InputStream.
     */
    public S3ObjectInputStreamWrapper downloadFileWithWatermark(String fileName) throws IOException, DocumentException {
        S3ObjectInputStreamWrapper originalFile = downloadFile(fileName);
        InputStream watermarkedStream = watermarkService.addWatermarkToPdf(originalFile.inputStream());
        // ETag is not applicable as the file is modified
        return new S3ObjectInputStreamWrapper(watermarkedStream, null);
    }

    /**
     * Returns a ResponseEntity with StreamingResponseBody and includes ETag in headers.
     */
    public ResponseEntity<StreamingResponseBody> downloadFileResponse(String fileName, boolean withWatermark) throws IOException, DocumentException {
        String contentType = Files.probeContentType(Paths.get(fileName));
        S3ObjectInputStreamWrapper fileWrapper = withWatermark ? downloadFileWithWatermark(fileName) : downloadFile(fileName);

        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = fileWrapper.inputStream()) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error occurred while streaming the file", e);
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
        if (fileWrapper.eTag() != null) {
            headers.add(HttpHeaders.ETAG, fileWrapper.eTag());
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    /**
     * Sanitizes the filename by removing special characters and replacing spaces.
     */
    private static String sanitizeFileName(String fileName) {
        String normalizedFileName = Normalizer.normalize(fileName, Normalizer.Form.NFKD);
        return normalizedFileName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9.\\-_]", "");
    }


}
