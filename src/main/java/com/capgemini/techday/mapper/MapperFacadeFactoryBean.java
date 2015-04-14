package com.capgemini.techday.mapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MapperFacadeFactoryBean implements FactoryBean<MapperFacade>, ApplicationContextAware {
    @Autowired(required = false)
    private Set<MappingConfigurer> configurers;
    @Autowired(required = false)
    private Set<CustomConverter<?, ?>> customConverters;

    @Override
    public MapperFacade getObject() throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        for (MappingConfigurer configurer : configurers) {
            configurer.configure(mapperFactory);
        }
        for (CustomConverter<?, ?> customConverter : customConverters) {
            mapperFactory.getConverterFactory().registerConverter(customConverter.getClass().getSimpleName(), customConverter);
        }

        return mapperFactory.getMapperFacade();

    }

    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
