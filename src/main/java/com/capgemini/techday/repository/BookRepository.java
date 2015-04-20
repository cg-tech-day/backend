package com.capgemini.techday.repository;

import com.capgemini.techday.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long>, QueryDslPredicateExecutor<BookEntity>, BookQuerydslRepository {

    List<BookEntity> findByTitleLike(String title);
}