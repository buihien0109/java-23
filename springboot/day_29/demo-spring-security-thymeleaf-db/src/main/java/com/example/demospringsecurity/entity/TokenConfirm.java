package com.example.demospringsecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "token_confirm")
public class TokenConfirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String token; // UUID

    @Enumerated(EnumType.STRING)
    TokenType type;

    LocalDateTime createdAt;
    LocalDateTime expiresAt;
    LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
