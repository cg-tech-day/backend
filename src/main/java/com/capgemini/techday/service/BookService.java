package com.capgemini.techday.service;

import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.rest.BookSearchCriteria;

import java.util.List;

public interface BookService {

    List<Book> findBooksByTitle(String title);
    List<Book> findBooksBySearchCriteria(BookSearchCriteria bookSearchCriteria);
}
