package com.capgemini.techday.service.impl;

import com.capgemini.techday.model.entity.AuthorEntity;
import com.capgemini.techday.model.entity.BookEntity;
import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.repository.BookRepository;
import com.capgemini.techday.rest.BookSearchCriteria;
import com.capgemini.techday.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findBooksByTitle(String title) {
        List<BookEntity> books = bookRepository.findByTitleLike(title);
        return mapBookEntityToBook(books);
    }

    @Override
    public List<Book> findBooksBySearchCriteria(BookSearchCriteria bookSearchCriteria) {
        final String title = bookSearchCriteria.getTitle();
        final List<String> authors = parseAuthorsParameter(bookSearchCriteria);
        final List<BookEntity> books = bookRepository.findBySearchCriteria(title, authors);
        return mapBookEntityToBook(books);
    }

    private List<String> parseAuthorsParameter(BookSearchCriteria bookSearchCriteria) {
        if (bookSearchCriteria.getAuthors() != null) {
            return Arrays.asList(bookSearchCriteria.getAuthors().split(","));
        }
        return null;
    }

    private List<Book> mapBookEntityToBook(List<BookEntity> entities) {
        return entities.stream().map(bookEntity -> new Book(bookEntity.getId(), convertAuthors2String(bookEntity.getAuthors()),
                bookEntity.getTitle())).collect(Collectors.toList());
    }

    private String convertAuthors2String(Set<AuthorEntity> authors) {
        if (authors == null || authors.isEmpty()) {
            return null;
        }
        StringBuilder authorsBuilder = new StringBuilder();
        Iterator<AuthorEntity> authorsIterator = authors.iterator();
        while (authorsIterator.hasNext()) {
            AuthorEntity authorEntity = authorsIterator.next();
            authorsBuilder.append(authorEntity.getFirstName()).append(" ").append(authorEntity.getLastName());
            if (authorsIterator.hasNext()) {
                authorsBuilder.append(", ");
            }
        }
        return authorsBuilder.toString();
    }
}
