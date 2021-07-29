package com.rusgorprojects.bookshareapp.model;

import java.util.List;

public class BookCreateRequest {

    private final String name;
    private final String description;
    private final String author;
    private final String isbn;           //ISBN-13
    private final Integer priceInCent;    // in EURO Cent
    private final List<String> tags;

    public BookCreateRequest(String name, String description, String author, String isbn, Integer priceInCent, List<String> tags) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPriceInCent() {
        return priceInCent;
    }

    public List<String> getTags() {
        return tags;
    }

}
