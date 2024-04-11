package com.example.demoapp.dao.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public Book findById(int id) {
        for (Book book : BookDB.books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
