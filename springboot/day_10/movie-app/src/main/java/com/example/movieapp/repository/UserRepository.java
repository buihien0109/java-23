package com.example.movieapp.repository;

import com.example.movieapp.entity.User;
import com.example.movieapp.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);
}