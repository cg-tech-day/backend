package com.capgemini.techday.model.to;

public class Book {
    private Long id;
    private String authors;
    private String title;

    public Book(Long id, String authors, String title) {
        this.id = id;
        this.authors = authors;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }
}
