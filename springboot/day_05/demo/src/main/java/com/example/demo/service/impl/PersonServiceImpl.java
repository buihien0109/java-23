package com.example.demo.service.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.response.PageResponse;
import com.example.demo.response.PageResponseImpl;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Override
    public List<Person> getAllPeople() {
        return personDAO.getAll();
    }

    // TODO: Triển khai với phân trang
    @Override
    public PageResponse<Person> getAllPeople(int page, int pageSize) {
        return new PageResponseImpl<>(personDAO.getAll(), page, pageSize);
    }
}
