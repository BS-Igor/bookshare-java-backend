package com.rusgorprojects.bookshareapp.controller;

import com.rusgorprojects.bookshareapp.model.BookCreateRequest;
import com.rusgorprojects.bookshareapp.model.BookResponse;
import com.rusgorprojects.bookshareapp.repository.BookRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<BookResponse> getAllBooks(
            @RequestParam(required = false) String tags
    ) {
        return bookRepository.findAll(tags);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(
            @PathVariable String id
    ){
        Optional<BookResponse> book = bookRepository.findById(id);
        if(book.isPresent())
            return ResponseEntity.ok(book.get());
        else{
            //return Response Entity
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    //für Frontend nützlicher die komplette erstellte Ressource zurückgeben
    public BookResponse createBook(@RequestBody BookCreateRequest request){
        return bookRepository.save(request);
    }




}