package com.rusgorprojects.bookshareapp.repository;

import com.rusgorprojects.bookshareapp.model.BookCreateRequest;
import com.rusgorprojects.bookshareapp.model.BookResponse;

import java.util.*;
import java.util.stream.Collectors;

//In dieser Klasse entsteht die Programmlogik

public class BookRepository {
    //Verwaltung der Bücher
    //gib uns ein Buch, finde ein Buch
    //BookRepository soll noch die Verbindung zur Datenbank beinhalten
    List<BookResponse> books = new ArrayList<>();

public BookRepository(){

        books.add(
                new BookResponse(
                        UUID.randomUUID().toString(),
                        "Herr der Fliegen",
                        "Die Fliegen machen Fliegensachen.",
                        "William Golding",
                        "978359621",
                        1900,
                        Arrays.asList("Roman", "Spannung", "Fantasy")
                ));
        books.add(
                new BookResponse(UUID.randomUUID().toString(),
                        "RISIKO",
                        "Wie man richtige Entscheidungen trifft.",
                        "Gerd Gigerenzer",
                        "978357055",
                        1440,
                        Arrays.asList("Risikokompetenz", "Fachlektüre")
                ));
        books.add(
                new BookResponse(UUID.randomUUID().toString(),
                        "Clean Coder",
                        "Verhaltensregeln für professionelle Programmierer.",
                        "Robert C. Martin",
                        "978382669",
                        1900,
                        Arrays.asList("Fachlektüre", "Programmieren")
                ));

    }

    public Optional<BookResponse> findById(String id) {
        Optional<BookResponse> book = books.stream().
                filter(p -> p.getId().equals(id))
                .findFirst();
        return book;
    }

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

    public void deleteById(String id) {
        this.books = books.stream().filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public BookResponse save(BookCreateRequest request) {
        BookResponse response = new BookResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPriceInCent(),
                request.getTags()
        );
        books.add(response);
        return response;
    }

 /*

                UUID.randomUUID().toString(),
                "Eine Minute für mich",
                "- ein sicheres Gefühl für innere Ruhe und Gleichgewicht - bessere Bezieh...",
                "Spencer Johnson",
                "3499601699",
                1200,
                Arrays.asList("Ratgeber","Lebenshilfe","Fachlektüre")

 */
}
