package dev.kananniftiyev.GameReviewHub.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.kananniftiyev.GameReviewHub.entity.Genre;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new AbstractConverter<Genre, String>() {
            @Override
            protected String convert(Genre source) {
                return source.getName(); // Map the name of the Genre
            }
        });

        return modelMapper;
    }
}
