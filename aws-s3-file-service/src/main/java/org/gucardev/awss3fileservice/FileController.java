package org.gucardev.awss3fileservice;


import com.amazonaws.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/upload/{objectKey}")
    public ResponseEntity<String> getPresignedUrlForUpload(@PathVariable String objectKey) {
        try {
            String url = fileService.generatePreSignedUrl(objectKey, HttpMethod.PUT);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to generate upload URL: " + e.getMessage());
        }
    }

    @GetMapping("/download/{objectKey}")
    public ResponseEntity<String> getPresignedUrlForDownload(@PathVariable String objectKey) {
        try {
            String url = fileService.generatePreSignedUrl(objectKey, HttpMethod.GET);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to generate download URL: " + e.getMessage());
        }
    }

}
