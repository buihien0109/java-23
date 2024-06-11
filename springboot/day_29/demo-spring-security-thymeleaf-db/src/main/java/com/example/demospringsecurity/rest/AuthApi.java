package com.example.demospringsecurity.rest;

import com.example.demospringsecurity.request.LoginRequest;
import com.example.demospringsecurity.request.RegisterRequest;
import com.example.demospringsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authService.login(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
}
