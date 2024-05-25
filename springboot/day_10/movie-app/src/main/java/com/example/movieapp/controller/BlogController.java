package com.example.movieapp.controller;

import com.example.movieapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public String getIndexPage(Model model) {
        // Lấy tất cả bài viết sắp xếp theo created giảm dần
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "admin/blog/index";
    }

    @GetMapping("/own-blogs")
    public String getOwnBlogPage(Model model) {
        // Lấy tất cả bài viết của user hiện tại (theo userID) sắp xếp theo created giảm dần
        model.addAttribute("blogs", blogService.getOwnBlogs());
        return "admin/blog/own";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "admin/blog/create";
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id));
        return "admin/blog/detail";
    }
}
