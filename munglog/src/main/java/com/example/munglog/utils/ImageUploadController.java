package com.example.munglog.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @Autowired
    private ImageUtil imageUtil;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = imageUtil.saveImage(file);
        return ResponseEntity.ok(imageUrl);
    }
}
