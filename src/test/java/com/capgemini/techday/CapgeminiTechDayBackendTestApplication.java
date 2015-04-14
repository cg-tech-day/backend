package com.capgemini.techday;

import com.capgemini.techday.util.MockedBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @Filter({MockedBean.class, SpringBootApplication.class}))
public class CapgeminiTechDayBackendTestApplication {

}
