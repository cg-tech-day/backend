package com.capgemini.techday.repository;

import com.capgemini.techday.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleLike(String title);

    @Query("select distinct book from BookEntity book left join book.authors as author where ((:title = null or book.title = :title) and ((:authors) = null or author.firstName in (:authors) or author.lastName in (:authors)))")
    List<BookEntity> findBySearchCriteria(@Param("title") String title, @Param("authors") List<String> authors);
}
