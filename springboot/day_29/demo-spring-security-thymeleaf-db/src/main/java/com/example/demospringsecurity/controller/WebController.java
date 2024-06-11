package com.example.demospringsecurity.controller;

import com.example.demospringsecurity.response.VerifyResponse;
import com.example.demospringsecurity.security.IsUser;
import com.example.demospringsecurity.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private final AuthService authService;

    public WebController(AuthService authService) {
        this.authService = authService;
    }

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

    // http://localhost:8080/xac-thuc-tai-khoan?token=298e0780-fb22-44bc-90b9-5d3b333e25f4
    @GetMapping("/xac-thuc-tai-khoan")
    public String getConfirmRegistrationPage(@RequestParam String token, Model model) {
        VerifyResponse response = authService.confirmRegistration(token);
        model.addAttribute("response", response);
        return "confirm-registration";
    }
}
