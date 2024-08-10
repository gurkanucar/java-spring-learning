package org.gucardev.fileuploadingwithio;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.text.Normalizer;
import java.util.EnumSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-dir}")
    private String baseDir;

    public String uploadMultipartFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + sanitizeFileName(file.getOriginalFilename());
        validateFile(file);
        Path filePath = Paths.get(baseDir, fileName);
        createParentDirectories(filePath);
        copyFile(file, filePath);
        setFilePermissions(filePath, getDefaultPermissions());
        return filePath.getFileName().toString();
    }

    private void validateFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }
    }

    private Set<PosixFilePermission> getDefaultPermissions() {
        return EnumSet.of(
                PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE,
                PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE
        );
    }

    private void createParentDirectories(Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
    }

    private void copyFile(MultipartFile file, Path filePath) throws IOException {
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void setFilePermissions(Path filePath, Set<PosixFilePermission> permissions) throws IOException {
        try {
            if (Files.getFileStore(filePath).supportsFileAttributeView("posix")) {
                Files.setPosixFilePermissions(filePath, permissions);
            } else {
                System.out.println("POSIX file permissions are not supported on this file system.");
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Setting POSIX file permissions is not supported on this operating system.");
        }
    }


    public InputStream downloadFile(String fileName) throws IOException {
        return Files.newInputStream(Paths.get(baseDir, fileName));
    }

    public ResponseEntity<StreamingResponseBody> downloadFileResponse(String fileName, boolean withWatermark) throws IOException {
        Path filePath = Paths.get(baseDir, fileName);
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = downloadFile(fileName)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        };
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .header("Content-Type", Files.probeContentType(filePath))
                .body(responseBody);
    }

    private String sanitizeFileName(String fileName) {
        String normalizedFileName = Normalizer.normalize(fileName, Normalizer.Form.NFKD);
        return normalizedFileName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9.\\-_]", "");
    }
}
