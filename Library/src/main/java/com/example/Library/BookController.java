package com.example.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books") // ✅ common base path
public class BookController {

    @Autowired
    private service service; // ✅ Corrected class name

    // ✅ Create (POST)
    @PostMapping("/add")
    public Book addBook(@RequestBody books books) throws SQLException {
        return service.addBook(books);
    }

    // ✅ Update (PUT)
    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) throws SQLException {
        return service.updateBook(book);
    }

    // ✅ Read single (GET)
    @GetMapping("/{id}")
    public Book viewBook(@PathVariable int id) throws SQLException {
        return service.viewBook(id);
    }

    // ✅ Delete (DELETE)
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) throws SQLException {
        service.deleteBook(id);
        return "Book with ID " + id + " deleted successfully.";
    }

    // ✅ Read all (GET)
    @GetMapping("/all")
    public List<books> getAllBooks() throws SQLException {
        return service.getAllBooks();
    }
}
