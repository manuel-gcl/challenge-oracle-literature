package com.alura.literature.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class LanguageListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return null;
        }
        return String.join(",", strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return Arrays.asList(string.split(","));
    }
}
