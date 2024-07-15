package org.gucardev.utilities.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

public class FileUtil {

    public static String readFileToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void writeStringToFile(String filePath, String data) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, data.getBytes());

        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
        Files.setPosixFilePermissions(path, perms);
    }

    public static List<String> readFileToList(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static void copyFile(String sourcePath, String destPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destPath));
    }

    public static void deleteFile(String filePath) throws IOException {
        Files.delete(Paths.get(filePath));
    }
}
