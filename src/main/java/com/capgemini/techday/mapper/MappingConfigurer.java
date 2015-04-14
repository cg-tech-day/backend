package com.capgemini.techday.mapper;

import ma.glasnost.orika.MapperFactory;

public interface MappingConfigurer {
    void configure(MapperFactory mapperFactory);
}
