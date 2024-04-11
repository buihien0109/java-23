package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Builder
@ToString
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class Student {
    int id;
    String name;

    public Student() {
        System.out.println("Student object created");
    }
}
