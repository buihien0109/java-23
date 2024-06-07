package com.example.demospringsecurity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ErrorResponse {
    HttpStatus status;
    Object message;
}
