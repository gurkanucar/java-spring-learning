package org.gucardev.fileuploadingwithio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "fileName", required = false) String fileName) {
        try {
            String savedFileName = fileService.storeFile(file, fileName);
            return ResponseEntity.ok("File uploaded successfully as " + savedFileName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Could not upload the file: " + e.getMessage());
        }
    }

    @GetMapping("/read/{fileName}")
    public ResponseEntity<byte[]> readFile(@PathVariable String fileName) {
        try {
            byte[] fileContent = fileService.readFile(fileName);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
