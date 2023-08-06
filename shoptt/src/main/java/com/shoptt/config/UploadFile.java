package com.shoptt.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadFile {

    public String upload(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream("src/main/resources/static/images/"+convFile);
        fos.write(file.getBytes());
        fos.close();
        return "../images/"+convFile.getName();
    }
}
