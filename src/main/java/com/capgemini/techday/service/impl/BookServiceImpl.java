package com.capgemini.techday.service.impl;

import com.capgemini.techday.model.entity.AuthorEntity;
import com.capgemini.techday.model.entity.BookEntity;
import com.capgemini.techday.model.entity.QBookEntity;
import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.repository.BookRepository;
import com.capgemini.techday.rest.BookSearchCriteria;
import com.capgemini.techday.service.BookService;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
//        final List<BookEntity> books = bookRepository.findBySearchCriteria(bookSearchCriteria);
        final Predicate searchPredicate = createSearchParameterPredicate(bookSearchCriteria);
        final List<BookEntity> books = convertIterableToList(bookRepository.findAll(searchPredicate));
        return mapBookEntityToBook(books);
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

    private Predicate createSearchParameterPredicate(BookSearchCriteria bookSearchCriteria) {
        final QBookEntity bookEntity = QBookEntity.bookEntity;
        final BooleanBuilder predicate = new BooleanBuilder();
        if (bookSearchCriteria != null && !StringUtils.isEmpty(bookSearchCriteria.getTitle())) {
            predicate.and(bookEntity.title.like(bookSearchCriteria.getTitle() + "%"));
        }
        if (bookSearchCriteria != null && !StringUtils.isEmpty(bookSearchCriteria.getAuthors())) {
            final String[] authors = bookSearchCriteria.getAuthors().split(",");
            predicate.and(bookEntity.authors.any().firstName.in(authors).or(bookEntity.authors.any().lastName.in(authors)));
        }
        return predicate;
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        if (iterable == null) {
            return new ArrayList<>();
        }
        if (iterable instanceof List) {
            return (List<T>) iterable;
        }
        List<T> result = new ArrayList<>();
        for (T next : iterable) {
            result.add(next);
        }
        return result;
    }
}
