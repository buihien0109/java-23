package com.example.demospringsecurity.controller;

import com.example.demospringsecurity.security.IsUser;
import org.springframework.security.access.prepost.PreAuthorize;
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
    // @Secured("ROLE_USER")
    @IsUser
    @GetMapping("/user")
    public String getUser() {
        return "user";
    }

    // Có role ADMIN mới truy cập được
    // @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }
}
