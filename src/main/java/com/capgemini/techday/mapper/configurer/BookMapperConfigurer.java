package com.capgemini.techday.mapper.configurer;

import com.capgemini.techday.mapper.MappingConfigurer;
import com.capgemini.techday.model.entity.BookEntity;
import com.capgemini.techday.model.to.Book;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BookMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(BookEntity.class, Book.class)
                .fieldMap("authors", "authors").converter("AuthorsConverter").add()
                .byDefault().register();
    }
}
