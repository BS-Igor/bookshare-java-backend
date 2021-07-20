package com.rusgorprojects.bookshareapp.repository;

import com.rusgorprojects.bookshareapp.model.BookResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    //Verwaltung der Bücher
    //gib uns ein Buch, finde ein Buch
    //BookRepository soll noch die Verbindung zur Datenbank beinhalten

    List<BookResponse> books = Arrays.asList(new BookResponse(
                    "0",
                    "Herr der Fliegen",
                    "Die Fliegen machen Fliegensachen.",
                    "William Golding",
                    978359621,
                    1900,
                    Arrays.asList("Roman", "Spannung", "ab18", "Fantasy")
            ),
            new BookResponse("1",
                    "RISIKO",
                    "Wie man richtige Entscheidungen trifft.",
                    "Gerd Gigerenzer",
                    978357055,
                    1440,
                    Arrays.asList("Risikokompetenz", "Fachlektüre")
            ),
            new BookResponse("2",
                    "Clean Coder",
                    "Verhaltensregeln für professionelle Programmierer.",
                    "Robert C. Martin",
                    978382669,
                    1900,
                    Arrays.asList("Fachlektüre", "Programmieren")
            )
    );

    public List<BookResponse> findAll(String tags) {


        if (tags == null)
            return books;
        else {
            String lowercaseTag = tags.toLowerCase();
            // ab Java8 gibt es dafür Stream-API
            List<BookResponse> filtered;  //vom Stream zurück zur Liste umwandeln
            filtered = books.stream()
                    .filter(b -> lowercaseTags(b).contains(lowercaseTag))
                    .collect(Collectors.toList());
            return filtered;
        }
    }

    private List<String> lowercaseTags(BookResponse b) {
        List<String> tags = b.getTags();
        return tags.stream()
                   .map(tag -> tag.toLowerCase())
                   .collect(Collectors.toList());
    }
}
