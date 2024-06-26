package com.example.movieapp.repository;

import com.example.movieapp.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdOrderByDisplayOrderAsc(Integer movieId);

    List<Episode> findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(Integer movieId, Boolean status);

    Optional<Episode> findByMovie_IdAndMovie_StatusAndDisplayOrder(Integer movieId, Boolean status, Integer displayOrder);
}