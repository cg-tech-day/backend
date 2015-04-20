package com.capgemini.techday.repository.impl;

import com.capgemini.techday.model.entity.BookEntity;
import com.capgemini.techday.model.entity.QBookEntity;
import com.capgemini.techday.repository.BookQuerydslRepository;
import com.capgemini.techday.rest.BookSearchCriteria;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookRepositoryImpl implements BookQuerydslRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookEntity> findBySearchCriteria(BookSearchCriteria bookSearchCriteria) {
        final QBookEntity bookEntity = QBookEntity.bookEntity;
        final JPAQuery query = new JPAQuery(entityManager, HQLTemplates.DEFAULT).from(bookEntity);

        final BooleanBuilder predicate = new BooleanBuilder();
        if (bookSearchCriteria != null && !StringUtils.isEmpty(bookSearchCriteria.getTitle())) {
            predicate.and(bookEntity.title.like(bookSearchCriteria.getTitle() + "%"));
        }
        if (bookSearchCriteria != null && !StringUtils.isEmpty(bookSearchCriteria.getAuthors())) {
            final String[] authors = bookSearchCriteria.getAuthors().split(",");
            predicate.and(bookEntity.authors.any().firstName.in(authors).or(bookEntity.authors.any().lastName.in(authors)));
        }

        query.where(predicate);
        return query.listResults(bookEntity).getResults();
    }
}
