package com.rusgorprojects.bookshareapp.model;

public class BookUpdateRequest {
    private final String name;
    private final String description;
    private final String author;
    private final String isbn;           //ISBN-13
    private final Integer priceInCent;

    public BookUpdateRequest(String name, String description, String author, String isbn, Integer priceInCent) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.priceInCent = priceInCent;
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
}
