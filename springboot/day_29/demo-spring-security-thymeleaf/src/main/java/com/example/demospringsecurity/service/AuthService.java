package com.example.demospringsecurity.service;

import com.example.demospringsecurity.exception.BadRequestException;
import com.example.demospringsecurity.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;

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
}
