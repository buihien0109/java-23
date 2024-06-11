package com.example.movieapp.repository;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByStatus(Boolean status);

    List<Movie> findByName(String name);

    List<Movie> findByNameIgnoreCase(String name);

    List<Movie> findByNameContaining(String keyword);

    List<Movie> findByNameAndSlug(String name, String slug);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingLessThanEqual(Double max);

    List<Movie> findByCreatedAtAfter(LocalDateTime createdAt);

    // Sắp xếp
    List<Movie> findByType(MovieType type, Sort sort);

    List<Movie> findByTypeOrderByRatingDesc(MovieType type);

    List<Movie> findByTypeOrderByCreatedAtDesc(MovieType type);

    Movie findFirstByTypeOrderByRatingDesc(MovieType type);

    // Đếm số lượng
    long countByStatus(Boolean status);

    // Kiểm tra tồn tại
    boolean existsByName(String name);

    // Phân trang
    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    List<Movie> findByTypeAndStatus(MovieType movieType, Boolean status, Sort sort);

    Page<Movie> findByTypeAndStatus(MovieType movieType, Boolean status, Pageable pageable);

    Optional<Movie> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);

    List<Movie> findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescCreatedAtDesc(MovieType type, Boolean status, double raying, Integer id);
}
