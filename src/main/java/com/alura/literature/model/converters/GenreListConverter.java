package com.alura.literature.model.converters;

import com.alura.literature.model.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class GenreListConverter implements AttributeConverter<List<Genre>, String> {
    @Override
    public String convertToDatabaseColumn(List<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return null;
        }
        return genres.stream()
                .map(Genre::name) // Convert enum to string
                .collect(Collectors.joining(",")); // Join with commas
    }

    @Override
    public List<Genre> convertToEntityAttribute(String genreString) {
        if (genreString == null || genreString.isEmpty()) {
            return null;
        }
        return Arrays.stream(genreString.split(",")) // Split by commas
                .map(Genre::valueOf) // Convert back to enum
                .collect(Collectors.toList());
    }
}
