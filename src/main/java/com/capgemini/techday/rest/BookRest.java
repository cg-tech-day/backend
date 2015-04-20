package com.capgemini.techday.rest;

import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRest {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> search(BookSearchCriteria bookSearchCriteria) {
        return bookService.findBooksBySearchCriteria(bookSearchCriteria);
    }
}
