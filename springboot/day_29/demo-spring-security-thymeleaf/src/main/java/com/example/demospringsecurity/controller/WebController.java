package com.example.demospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // Ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    // Có role USER mới truy cập được
    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    // Có role ADMIN mới truy cập được
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
