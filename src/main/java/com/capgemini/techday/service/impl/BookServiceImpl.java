package com.capgemini.techday.service.impl;

import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.repository.BookRepository;
import com.capgemini.techday.rest.BookSearchCriteria;
import com.capgemini.techday.service.BookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MapperFacade mapper;

    @Override
    public List<Book> findBooksByTitle(String title) {
        return mapper.mapAsList(bookRepository.findByTitleLike(title), Book.class);
    }

    @Override
    public List<Book> findBooksBySearchCriteria(BookSearchCriteria bookSearchCriteria) {
        final String title = bookSearchCriteria.getTitle();
        final List<String > authors = parseAuthorsParameter(bookSearchCriteria);
        return mapper.mapAsList(bookRepository.findBySearchCriteria(title, authors), Book.class);
    }

    private List<String> parseAuthorsParameter(BookSearchCriteria bookSearchCriteria) {
        if (bookSearchCriteria.getAuthors() != null) {
            return Arrays.asList(bookSearchCriteria.getAuthors().split(","));
        }
        return null;
    }
}
