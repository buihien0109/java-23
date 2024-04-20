package com.example.demo.utils;

import com.example.demo.model.Person;

import java.util.List;

public interface IFileReader {
    List<Person> readFile(String path);
}
