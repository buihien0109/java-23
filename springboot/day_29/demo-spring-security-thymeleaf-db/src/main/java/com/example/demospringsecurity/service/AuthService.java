package com.example.demospringsecurity.service;

import com.example.demospringsecurity.entity.Role;
import com.example.demospringsecurity.entity.TokenConfirm;
import com.example.demospringsecurity.entity.TokenType;
import com.example.demospringsecurity.entity.User;
import com.example.demospringsecurity.exception.BadRequestException;
import com.example.demospringsecurity.repository.RoleRepository;
import com.example.demospringsecurity.repository.TokenConfirmRepository;
import com.example.demospringsecurity.repository.UserRepository;
import com.example.demospringsecurity.request.LoginRequest;
import com.example.demospringsecurity.request.RegisterRequest;
import com.example.demospringsecurity.response.VerifyResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TokenConfirmRepository tokenConfirmRepository;

    public void login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("MY_SESSION", authentication.getName());
        } catch (DisabledException e) {
            throw new BadRequestException("Tài khoản chưa được kích hoạt");
        } catch (AuthenticationException e) {
            throw new BadRequestException("Email hoặc mật khẩu không đúng");
        }
    }

    public String register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }

        // Tạo user
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new BadRequestException("Role không tồn tại"));
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(newUser);

        // Tạo token xác thực
        TokenConfirm token = TokenConfirm.builder()
                .token(UUID.randomUUID().toString())
                .type(TokenType.REGISTRATION)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(newUser)
                .build();
        tokenConfirmRepository.save(token);

        // Tạo link xác thực
        return "http://localhost:8080/xac-thuc-tai-khoan?token=" + token.getToken();
    }

    public VerifyResponse confirmRegistration(String token) {
        Optional<TokenConfirm> tokenConfirmOptional = tokenConfirmRepository
                .findByTokenAndType(token, TokenType.REGISTRATION);

        // Kiểm tra token
        if (tokenConfirmOptional.isEmpty()) {
            return VerifyResponse.builder()
                    .message("Token không hợp lệ")
                    .success(false)
                    .build();
        }

        TokenConfirm tokenConfirm = tokenConfirmOptional.get();
        // Token đã được xác thực hay chưa
        if (tokenConfirm.getConfirmedAt() != null) {
            return VerifyResponse.builder()
                    .message("Token đã được xác thực")
                    .success(false)
                    .build();
        }

        // Token đã hết hạn hay chưa
        if (tokenConfirm.getExpiresAt().isBefore(LocalDateTime.now())) {
            return VerifyResponse.builder()
                    .message("Token đã hết hạn")
                    .success(false)
                    .build();
        }

        // Active user
        User user = tokenConfirm.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        // Xác thực token
        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);

        return VerifyResponse.builder()
                .message("Xác thực tài khoản thành công")
                .success(true)
                .build();
    }
}
