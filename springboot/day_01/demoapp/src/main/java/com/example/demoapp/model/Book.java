package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    int id;
    String title;
    String author;
    int year;
}
