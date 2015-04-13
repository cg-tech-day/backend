package com.capgemini.techday.rest;

import com.capgemini.techday.model.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BookRest {
    @RequestMapping("/books")
    public List<Book> search(BookSearchCriteria bookSearchCriteria) {
        return Collections.singletonList(new Book(1234L, "D. Crockford", "JavaScript. The Good Parts"));
    }
}
