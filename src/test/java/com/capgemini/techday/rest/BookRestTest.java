package com.capgemini.techday.rest;

import com.capgemini.techday.CapgeminiTechDayBackendTestApplication;
import com.capgemini.techday.mocks.BookServiceMock;
import com.capgemini.techday.model.to.Book;
import com.capgemini.techday.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {CapgeminiTechDayBackendTestApplication.class, BookServiceMock.class})
public class BookRestTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private BookService bookService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Mockito.reset(bookService);
    }

    @Test
    public void testName() throws Exception {
        // given
        Book mockedBook = new Book(1L, "Authors", "Title");
        when(bookService.findBooksBySearchCriteria(any(BookSearchCriteria.class))).thenReturn(Arrays.asList(mockedBook));
        // when
        ResultActions response = mockMvc.perform(get("/books?authors=AuthorsParameter&title=TitleParameter")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].title").value("Title"))
                .andExpect(jsonPath("[0].authors").value("Authors"));

        ArgumentCaptor<BookSearchCriteria> bookSearchCriteriaArgumentCaptor = ArgumentCaptor.forClass(BookSearchCriteria.class);
        verify(bookService).findBooksBySearchCriteria(bookSearchCriteriaArgumentCaptor.capture());
        assertEquals("TitleParameter", bookSearchCriteriaArgumentCaptor.getValue().getTitle());
        assertEquals("AuthorsParameter", bookSearchCriteriaArgumentCaptor.getValue().getAuthors());
    }
}
