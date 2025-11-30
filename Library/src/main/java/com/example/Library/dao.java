package com.example.Library;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class dao {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "prabhat";

    // ✅ Add Book (don’t set ID — auto increment)
    public books addBook(books book) throws SQLException {
        String sql = "INSERT INTO books(name, author, price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPrice());
            ps.executeUpdate();

            // ✅ retrieve auto-generated id
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            }
        }
        System.out.println("Book added successfully");
        return book;
    }

    // ✅ Update Book
    public books updateBook(books book) throws SQLException {
        String sql = "UPDATE books SET name = ?, author = ?, price = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPrice());
            ps.setInt(4, book.getId());
            ps.executeUpdate();
        }

        System.out.println("Book updated successfully");
        return book;
    }

    // ✅ View Single Book by ID
    public books viewBook(books id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";
        books book = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    book = new books();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getInt("price"));
                }
            }
        }

        return book;
    }

    // ✅ Delete Book
    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }

        System.out.println("Book deleted successfully");
    }

    // ✅ Get All Books
    public List<books> getAllBooks() throws SQLException {
        List<books> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books book = new books();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }
        }

        return list;
    }
}
