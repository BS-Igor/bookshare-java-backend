package com.rusgorprojects.bookshareapp.controller;

import com.rusgorprojects.bookshareapp.model.BookResponse;
import com.rusgorprojects.bookshareapp.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BookController {

    BookRepository bookRepository = new BookRepository();

    @GetMapping("/books")
    public List<BookResponse> getAllBooks(
            @RequestParam(required = false) String tags
    ) {

        return bookRepository.findAll(tags);
    }

}