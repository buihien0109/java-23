package com.example.demospringsecurity.controller;

import com.example.demospringsecurity.security.IAuthenticationFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    // Ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "Home Page";
    }

    // Có role USER mới truy cập được
    @GetMapping("/user")
    public String getUser() {
        return "User Page";
    }

    // Có role ADMIN mới truy cập được
    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin Page";
    }

    @GetMapping("/info1")
    public ResponseEntity<?> getInfo1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info2")
    public ResponseEntity<?> getInfo2(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info3")
    public ResponseEntity<?> getInfo3(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info4")
    public ResponseEntity<?> getInfo4(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info5")
    public ResponseEntity<?> getInfo5() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
