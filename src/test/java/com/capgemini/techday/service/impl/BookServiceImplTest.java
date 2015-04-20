package com.capgemini.techday.service.impl;

import com.capgemini.techday.CapgeminiTechDayBackendTestApplication;
import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.rest.BookSearchCriteria;
import com.capgemini.techday.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = CapgeminiTechDayBackendTestApplication.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindBookByTitle() {
        // given
        final String title = "Praca Zbiorowa";
        // when
        List<Book> books = bookService.findBooksByTitle(title);
        // then
        assertEquals("Result should find one book", 1, books.size());
        assertEquals("Book should have three authors", 3, books.get(0).getAuthors().split(",").length);
    }

    @Test
    public void testShouldFindBooksByEmptySearchCriteria() {
        // given
        final BookSearchCriteria searchCriteria = new BookSearchCriteria();
        // when
        List<Book> books = bookService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertFalse(books.isEmpty());
    }

    @Test
    public void testShouldFindBooksByNullSearchCriteria() {
        // given when
        List<Book> books = bookService.findBooksBySearchCriteria(null);
        // then
        assertFalse(books.isEmpty());
    }

    @Test
    public void testShouldFindBookByTitleSearchCriteria() {
        // given
        final BookSearchCriteria searchCriteria = new BookSearchCriteria();
        searchCriteria.setTitle("Praca Zbiorowa");
        // when
        List<Book> books = bookService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals("Result should find one book", 1, books.size());
    }

    @Test
    public void testShouldFindBookByAuthorsSearchCriteria() {
        // given
        final BookSearchCriteria searchCriteria = new BookSearchCriteria();
        searchCriteria.setAuthors("Michaił,Prus");
        // when
        List<Book> books = bookService.findBooksBySearchCriteria(searchCriteria);
        // then
        assertEquals("Result should find two books", 2, books.size());
    }
}
