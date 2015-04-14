package com.capgemini.techday.mapper.converter;

import com.capgemini.techday.model.entity.AuthorEntity;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
public class AuthorsConverter extends CustomConverter<Set<AuthorEntity>, String> {

    @Override
    public String convert(Set<AuthorEntity> source, Type<? extends String> destinationType) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        StringBuilder authorsBuilder = new StringBuilder();
        Iterator<AuthorEntity> authorsIterator = source.iterator();
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
