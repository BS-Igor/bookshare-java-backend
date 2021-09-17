package com.rusgorprojects.bookshareapp.controller;

import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException;
import com.rusgorprojects.bookshareapp.model.BookCreateRequest;
import com.rusgorprojects.bookshareapp.model.BookResponse;
import com.rusgorprojects.bookshareapp.model.BookUpdateRequest;
import com.rusgorprojects.bookshareapp.repository.BookRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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
        BookResponse response = new BookResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPriceInCent(),
                request.getTags()
        );
        return bookRepository.save(response);
    }

    @PutMapping("/books/{id}")
    public BookResponse updateBook(
            @RequestBody BookUpdateRequest request,
            @PathVariable String id
    ){
      final BookResponse book = bookRepository.findById(id)
                .orElseThrow(() ->
                                new IdNotFoundException(
                                        "Book with id " + id + " not found",
                                        HttpStatus.BAD_REQUEST
                                )
                        );

      final BookResponse updatedBook = new BookResponse(
              book.getId(),
              request.getName() == null ? book.getName() : request.getName(),
              request.getDescription() == null ? book.getDescription() : request.getDescription(),
              request.getAuthor() == null ? book.getAuthor() : request.getAuthor(),
              request.getIsbn() == null ? book.getIsbn() : request.getIsbn(),
              request.getPriceInCent() == null ? book.getPriceInCent() : request.getPriceInCent(),
              book.getTags()
      );

        return bookRepository.save(updatedBook);
    }

}