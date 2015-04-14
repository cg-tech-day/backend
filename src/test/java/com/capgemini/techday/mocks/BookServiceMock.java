package com.capgemini.techday.mocks;

import com.capgemini.techday.service.BookService;
import com.capgemini.techday.util.MockedBean;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MockedBean
public class BookServiceMock {

    @Bean
    @Primary
    public BookService registerBookServiceMock() {
        return Mockito.mock(BookService.class);
    }
}
