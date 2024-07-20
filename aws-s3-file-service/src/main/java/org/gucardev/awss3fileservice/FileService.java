package org.gucardev.awss3fileservice;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${aws.bucket}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    public String generatePreSignedUrl(String filePath, HttpMethod method) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(filePath, method);
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_CANNED_ACL, "private");
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String filePath, HttpMethod method) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        return new GeneratePresignedUrlRequest(bucketName, filePath)
                .withMethod(method)
                .withExpiration(expiration);
    }
}
