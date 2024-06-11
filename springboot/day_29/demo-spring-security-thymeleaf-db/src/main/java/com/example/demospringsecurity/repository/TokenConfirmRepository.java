package com.example.demospringsecurity.repository;

import com.example.demospringsecurity.entity.TokenConfirm;
import com.example.demospringsecurity.entity.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
    Optional<TokenConfirm> findByTokenAndType(String token, TokenType tokenType);
}