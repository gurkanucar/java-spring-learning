package org.gucardev.awss3fileservice;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpMethod;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.Normalizer;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${aws.bucket}")
    private String bucketName;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final WatermarkService watermarkService;

    public String generatePreSignedUrl(String filePath, SdkHttpMethod method) {
        if (method == SdkHttpMethod.GET) {
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
        } else if (method == SdkHttpMethod.PUT) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filePath)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(60))
                    .putObjectRequest(putObjectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
            return presignedRequest.url().toString();
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
    }

    public String generatePublicPreSignedUrl(String filePath, SdkHttpMethod method) {
        if (method == SdkHttpMethod.PUT) {
            // Presigned URL Generation with Public Read ACL
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filePath)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(60))
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
            return presignedRequest.url().toString();
        } else if (method == SdkHttpMethod.GET) {
            return generatePreSignedUrl(filePath, method);
        } else {
            throw new UnsupportedOperationException("Unsupported HTTP method: " + method);
        }
    }


    public List<MultiplePreSignedUrlResponse> generateMultiplePublicPreSignedUrls(List<MultiplePreSignedUrlRequest> requests) {
        List<MultiplePreSignedUrlResponse> urls = new ArrayList<>();
        for (MultiplePreSignedUrlRequest request : requests) {
            var filename = buildFilename(request.getOriginalFileName());
            String url = generatePublicPreSignedUrl(filename, SdkHttpMethod.PUT);
            MultiplePreSignedUrlResponse response = new MultiplePreSignedUrlResponse(url, filename, request.getOriginalFileName());
            urls.add(response);
        }
        return urls;
    }

    public static String buildFilename(String filename) {
        return "%s_%s".formatted(System.currentTimeMillis(), sanitizeFileName(filename));
    }

    public String uploadMultipartFile(MultipartFile file) throws IOException {
        String fileName = buildFilename(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
        }
        return fileName;
    }

    public InputStream downloadFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        return s3Client.getObject(getObjectRequest);
    }

    public InputStream downloadFileWithWatermark(String fileName) throws IOException, DocumentException {
        InputStream originalFileStream = downloadFile(fileName);
        return watermarkService.addWatermarkToPdf(originalFileStream);
    }

    public ResponseEntity<StreamingResponseBody> downloadFileResponse(String fileName, boolean withWatermark) throws IOException, DocumentException {
        String contentType = Files.probeContentType(Paths.get(fileName));
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = withWatermark ? downloadFileWithWatermark(fileName) : downloadFile(fileName)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException | DocumentException e) {
                throw new RuntimeException("Error occurred while streaming the file", e);
            }
        };
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", contentType)
                .body(responseBody);
    }

    private static String sanitizeFileName(String fileName) {
        String normalizedFileName = Normalizer.normalize(fileName, Normalizer.Form.NFKD);
        return normalizedFileName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9.\\-_]", "");
    }
}
