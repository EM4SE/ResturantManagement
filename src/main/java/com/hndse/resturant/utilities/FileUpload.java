package com.hndse.resturant.utilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUpload {
    public static String saveFile(String uploadDir, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = generateUniqueFileName(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new IOException("Could not save file: " + filename, e);
        }
    }

    public static void deleteFile(String uploadDir, String filename) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(filename);
        Files.deleteIfExists(filePath);
    }

    private static String generateUniqueFileName(String originalFileName) {
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        return UUID.randomUUID().toString() + extension;
    }

    public static boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null) {
            return false;
        }
        return contentType.startsWith("image/");
    }

    public static void validateFile(MultipartFile file, long maxSize, String... allowedTypes) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        if (file.getSize() > maxSize) {
            throw new IOException("File size exceeds maximum limit");
        }

        String contentType = file.getContentType();
        boolean isValidType = false;

        for (String type : allowedTypes) {
            if (contentType != null && contentType.equals(type)) {
                isValidType = true;
                break;
            }
        }

        if (!isValidType) {
            throw new IOException("Invalid file type");
        }
    }
}
