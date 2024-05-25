package com.example.movieapp.service;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.repository.BlogRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final HttpSession session;
    private final FileService fileService;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Blog> getOwnBlogs() {
        User user = (User) session.getAttribute("currentUser");
        return blogRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
    }

    public String uploadThumbnail(Integer id, MultipartFile file) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        try {
            Map data = fileService.uploadImage(file);
            String url = (String) data.get("url");
            blog.setThumbnail(url);
            blogRepository.save(blog);

            return url;
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file");
        }
    }
}
