package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;

import java.util.List;

public interface PersonService {
    List<Person> getAllPeople();

    PageResponse<Person> getAllPeople(int page, int pageSize);
}
