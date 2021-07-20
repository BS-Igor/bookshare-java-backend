package com.rusgorprojects.bookshareapp.controller;

import com.rusgorprojects.bookshareapp.model.BookResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class BookController {

    public List<BookResponse> getAllBooks(){
         return Arrays.asList(
                 new BookResponse("0","Herr der Fliegen", "Die Fliegen machen Fliegensachen.",1900,Arrays.asList("Roman","Spannung","ab18"))
         );
    }
}
