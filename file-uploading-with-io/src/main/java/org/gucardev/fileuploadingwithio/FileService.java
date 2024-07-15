package org.gucardev.fileuploadingwithio;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-dir}")
    private String baseDir;

    public String storeFile(MultipartFile file, String fileName) throws IOException {
        validateFile(file);
        Path filePath = buildFilePath(file, fileName);
        Set<PosixFilePermission> permissions = getDefaultPermissions();
        createParentDirectories(filePath);
        copyFile(file, filePath);
        setFilePermissions(filePath, permissions);
        return filePath.getFileName().toString();
    }

    private void validateFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }
    }

    private Path buildFilePath(MultipartFile file, String fileName) {
        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String finalFileName = (fileName == null || fileName.isEmpty())
                ? UUID.randomUUID() + extension
                : fileName + extension;
        return Paths.get(baseDir + File.separator + finalFileName);
    }

    private Set<PosixFilePermission> getDefaultPermissions() {
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
        return perms;
    }

    private void createParentDirectories(Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
    }

    private void copyFile(MultipartFile file, Path filePath) throws IOException {
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void setFilePermissions(Path filePath, Set<PosixFilePermission> permissions) throws IOException {
        Files.setPosixFilePermissions(filePath, permissions);
    }

    public byte[] readFile(String fileName) throws IOException {
        Path filePath = Paths.get(baseDir + File.separator + fileName);
        return Files.readAllBytes(filePath);
    }
}
