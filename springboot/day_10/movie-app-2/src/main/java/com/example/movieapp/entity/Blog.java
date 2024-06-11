package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "TEXT")
    String content;

    String thumbnail;
    Boolean status;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
