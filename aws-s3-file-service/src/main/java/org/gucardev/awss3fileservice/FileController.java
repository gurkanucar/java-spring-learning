package org.gucardev.awss3fileservice;


import com.amazonaws.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{filename}")
    public ResponseEntity<String> getUrl(@PathVariable String filename) {
        return ResponseEntity.ok(fileService.generatePreSignedUrl(filename, HttpMethod.GET));
    }

    @PostMapping("/pre-signed-url")
    public ResponseEntity<Map<String, Object>> generateUrl(@RequestParam String extension) {
        var file = UUID.randomUUID() + "." + extension;
        var response = fileService.generatePreSignedUrl(file, HttpMethod.PUT);
        return ResponseEntity.ok(Map.of("url", response, "file", file));
    }

    @PostMapping("/public-pre-signed-url")
    public ResponseEntity<Map<String, Object>> generatePublicUrl(@RequestParam String extension) {
        var file = UUID.randomUUID() + "." + extension;
        var response = fileService.generatePublicPreSignedUrl(file, HttpMethod.PUT);
        return ResponseEntity.ok(Map.of("url", response, "file", file));
    }


    @PostMapping("/public-pre-signed-urls")
    public ResponseEntity<Map<String, List<MultiplePreSignedUrlResponse>>> generatePublicUrls(@RequestBody List<MultiplePreSignedUrlRequest> request) {
        List<MultiplePreSignedUrlResponse> urls = fileService.generateMultiplePublicPreSignedUrls(request);
        return ResponseEntity.ok(Map.of("urls", urls));
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileService.uploadMultipartFile(file);
        return ResponseEntity.ok("File name: " + fileName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable String fileName) throws IOException {
        String contentType = Files.probeContentType(Paths.get(fileName));

        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = fileService.downloadFile(fileName)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error while streaming the file", e);
            }
        };

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", contentType)
                .body(responseBody);
    }


}
