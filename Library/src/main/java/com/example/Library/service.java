package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

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

    public List<books> getAllBooks() throws SQLException {
        return dao.getAllBooks();
    }
}
