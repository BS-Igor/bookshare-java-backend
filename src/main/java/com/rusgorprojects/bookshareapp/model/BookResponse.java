package com.rusgorprojects.bookshareapp.model;

import java.util.List;

public class BookResponse {

    private final String id;
    private final String name;
    private final String description;
    private final String author;
    private final long isbn;           //ISBN-13
    private final Integer priceInCent;    // in EURO Cent
    private final List<String> tags;

    public BookResponse(String id, String name, String description, String author, long isbn, Integer priceInCent, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }

    public String getId() {
        return id;
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

    public long getIsbn() {
        return isbn;
    }

    public Integer getPriceInCent() {
        return priceInCent;
    }

    public List<String> getTags() {
        return tags;
    }

}
