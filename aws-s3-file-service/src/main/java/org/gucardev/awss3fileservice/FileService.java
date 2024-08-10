package org.gucardev.awss3fileservice;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${aws.bucket}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final WatermarkService watermarkService;

    private String sanitizeFileName(String fileName) {
        String normalizedFileName = Normalizer.normalize(fileName, Normalizer.Form.NFKD);
        String fileNameWithUnderscores = normalizedFileName.replaceAll("\\s+", "_");
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9.\\-_]");
        return pattern.matcher(fileNameWithUnderscores).replaceAll("");
    }


    public String generatePreSignedUrl(String filePath, HttpMethod method) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(filePath, method);
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String generatePublicPreSignedUrl(String filePath, HttpMethod method) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(filePath, method);
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_CANNED_ACL, "public-read");
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public List<MultiplePreSignedUrlResponse> generateMultiplePublicPreSignedUrls(List<MultiplePreSignedUrlRequest> request) {
        List<MultiplePreSignedUrlResponse> urls = new ArrayList<>();
        for (MultiplePreSignedUrlRequest multiplePreSignedUrlRequest : request) {
            String filename = UUID.randomUUID() + "." + multiplePreSignedUrlRequest.getExtension();
            String url = generatePublicPreSignedUrl(filename, HttpMethod.PUT);
            MultiplePreSignedUrlResponse response = new MultiplePreSignedUrlResponse(url, filename, multiplePreSignedUrlRequest.getOriginalFileName());
            urls.add(response);
        }
        return urls;
    }

    private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String filePath, HttpMethod method) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 60 minutes
        expiration.setTime(expTimeMillis);
        return new GeneratePresignedUrlRequest(bucketName, filePath)
                .withMethod(method)
                .withExpiration(expiration);
    }

    public String uploadMultipartFile(MultipartFile file) throws IOException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + sanitizeFileName(file.getOriginalFilename());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        return fileName;
    }

    public InputStream downloadFile(String fileName) throws IOException {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        return s3Object.getObjectContent();
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


    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }
}
