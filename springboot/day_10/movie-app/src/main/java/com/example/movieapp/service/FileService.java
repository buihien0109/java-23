package com.example.movieapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {
    private final String UPLOAD_FOLDER =  "upload_image_23";
    private final Cloudinary cloudinary;

    public Map uploadImage(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "folder", UPLOAD_FOLDER
                ));
    }
}
