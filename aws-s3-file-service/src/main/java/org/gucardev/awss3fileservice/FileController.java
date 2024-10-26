package org.gucardev.awss3fileservice;


import software.amazon.awssdk.http.SdkHttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.gucardev.awss3fileservice.FileService.buildFilename;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{filename}")
    public ResponseEntity<String> getUrl(@PathVariable String filename) {
        return ResponseEntity.ok(fileService.generatePreSignedUrl(filename, SdkHttpMethod.GET));
    }

    @PostMapping("/pre-signed-url")
    public ResponseEntity<Map<String, Object>> generateUrl(@RequestParam(name = "filename", required = false, defaultValue = "") String filename) {
        filename = buildFilename(filename);
        var response = fileService.generatePreSignedUrl(filename, SdkHttpMethod.PUT);
        return ResponseEntity.ok(Map.of("url", response, "file", filename));
    }

    @PostMapping("/public-pre-signed-url")
    public ResponseEntity<Map<String, Object>> generatePublicUrl(@RequestParam(name = "filename", required = false, defaultValue = "") String filename) {
        filename = buildFilename(filename);
        var response = fileService.generatePublicPreSignedUrl(filename, SdkHttpMethod.PUT);
        return ResponseEntity.ok(Map.of("url", response, "file", filename));
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
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable("fileName") String fileName) throws Exception {
        return fileService.downloadFileResponse(fileName, false);
    }

    @GetMapping("/downloadWithWatermark/{fileName}")
    public ResponseEntity<StreamingResponseBody> downloadFileWithWatermark(@PathVariable("fileName") String fileName) throws Exception {
        return fileService.downloadFileResponse(fileName, true);
    }


}
