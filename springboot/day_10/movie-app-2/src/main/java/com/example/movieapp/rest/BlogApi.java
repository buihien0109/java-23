package com.example.movieapp.rest;

import com.example.movieapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class BlogApi {
    private final BlogService blogService;

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable Integer id,
                                             @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(blogService.uploadThumbnail(id, file));
    }
}
