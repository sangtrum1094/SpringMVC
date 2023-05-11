package com.sang.minishops.until;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        File uploadDirPath = new File(uploadDir);

        if (!uploadDirPath.exists()) {
            uploadDirPath.mkdir();
        }

        File file = new File(uploadDirPath, fileName);
        multipartFile.transferTo(file);
    }
}
