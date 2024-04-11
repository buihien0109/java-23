package com.example.demoapp.service.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.findById(id);
    }
}
