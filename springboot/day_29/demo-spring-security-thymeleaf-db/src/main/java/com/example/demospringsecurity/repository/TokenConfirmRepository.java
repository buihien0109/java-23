package com.example.demospringsecurity.repository;

import com.example.demospringsecurity.entity.TokenConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
}