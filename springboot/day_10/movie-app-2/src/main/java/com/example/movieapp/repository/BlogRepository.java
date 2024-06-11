package com.example.movieapp.repository;

import com.example.movieapp.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByUser_IdOrderByCreatedAtDesc(Integer id);
}