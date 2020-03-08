package com.github.jvanheesch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.withEntityLookup().forValueRepository(AuthorRepository.class, Author::getId, AuthorRepository::findById);
    }

    @Bean
    public List<BackendIdConverter> customIdConverters() {
        return Collections.singletonList(new BackendIdConverter() {
            @Override
            public Serializable fromRequestId(String id, Class<?> entityType) {
                return Long.parseLong(id);
            }

            @Override
            public String toRequestId(Serializable id, Class<?> entityType) {
                return id.toString();
            }

            @Override
            public boolean supports(Class<?> aClass) {
                return aClass.equals(Author.class);
            }
        });
    }
}
