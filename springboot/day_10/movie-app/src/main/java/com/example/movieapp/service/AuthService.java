package com.example.movieapp.service;

import com.example.movieapp.entity.User;
import com.example.movieapp.model.request.LoginRequest;
import com.example.movieapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        // Kiem tra xem user co ton tai trong database khong
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email incorrect"));

        // Kiểm tra xem password có khớp với password trong database không
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        // Lưu thông tin user vào trong session để sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }
}
