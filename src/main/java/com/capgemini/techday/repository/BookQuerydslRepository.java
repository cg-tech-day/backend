package com.capgemini.techday.repository;

import com.capgemini.techday.model.entity.BookEntity;
import com.capgemini.techday.rest.BookSearchCriteria;

import java.util.List;

public interface BookQuerydslRepository {

    List<BookEntity> findBySearchCriteria(BookSearchCriteria bookSearchCriteria);
}
