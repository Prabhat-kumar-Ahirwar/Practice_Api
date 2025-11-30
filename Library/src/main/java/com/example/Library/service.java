package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class service {
    @Autowired
    dao dao;
    public  books add(books books) throws SQLException {

        return dao.addBook(books);
    }
    public books updateBook(books books) throws SQLException {
        return dao.updateBook(books);
    }

    public books viewBook(books books) throws SQLException {
        return dao.viewBook(books);
    }
    public books deleteBook(books books) throws SQLException {
        return dao.updateBook(books);
    }
}
