package com.apple.agriculture.service;

import jakarta.persistence.AttributeConverter;

import java.time.Year;

public class YearToStringConverter implements AttributeConverter<Year, String> {
    @Override
    public String convertToDatabaseColumn(Year attribute) {
        return attribute.toString();
    }

    @Override
    public Year convertToEntityAttribute(String dbData) {
        return Year.parse(dbData);
    }
}
