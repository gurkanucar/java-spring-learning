package org.gucardev.awss3fileservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import software.amazon.awssdk.http.SdkHttpMethod;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.gucardev.awss3fileservice.FileService.buildFilename;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * Generates a presigned GET URL for a file.
     */
    @GetMapping("/{filename}")
    public ResponseEntity<String> getUrl(@PathVariable String filename) {
        String url = fileService.generatePreSignedUrl(filename, SdkHttpMethod.GET, null);
        return ResponseEntity.ok(url);
    }

    /**
     * Generates a presigned PUT URL with specified access type.
     */
    @PostMapping("/pre-signed-url")
    public ResponseEntity<Map<String, Object>> generateUrl(
            @RequestParam(name = "filename", required = false, defaultValue = "") String filename,
            @RequestParam(name = "accessType", required = false, defaultValue = "PRIVATE") AccessType accessType) {
        filename = buildFilename(filename);
        String url = fileService.generatePreSignedUrl(filename, SdkHttpMethod.PUT, accessType);
        return ResponseEntity.ok(Map.of("url", url, "file", filename));
    }

    /**
     * Generates multiple presigned PUT URLs with specified access type.
     */
    @PostMapping("/pre-signed-urls")
    public ResponseEntity<Map<String, List<MultiplePreSignedUrlResponse>>> generateUrls(
            @RequestBody List<MultiplePreSignedUrlRequest> request,
            @RequestParam(name = "accessType", required = false, defaultValue = "PRIVATE") AccessType accessType) {
        List<MultiplePreSignedUrlResponse> urls = fileService.generateMultiplePreSignedUrls(request, accessType);
        return ResponseEntity.ok(Map.of("urls", urls));
    }

    /**
     * Uploads a file with specified access type.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "accessType", required = false, defaultValue = "PRIVATE") AccessType accessType) throws IOException {
        String fileName = fileService.uploadMultipartFile(file, accessType);
        return ResponseEntity.ok("File name: " + fileName);
    }

    /**
     * Downloads a file.
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<StreamingResponseBody> downloadFile(@PathVariable("fileName") String fileName) throws Exception {
        return fileService.downloadFileResponse(fileName, false);
    }

    /**
     * Downloads a file with a watermark.
     */
    @GetMapping("/downloadWithWatermark/{fileName}")
    public ResponseEntity<StreamingResponseBody> downloadFileWithWatermark(@PathVariable("fileName") String fileName) throws Exception {
        return fileService.downloadFileResponse(fileName, true);
    }
}
