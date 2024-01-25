package com.apple.agriculture.service;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class YearToStringConverterTest {

    YearToStringConverter converter = new YearToStringConverter();

    @Test
    void shouldConvertYearToStringDatabaseColumn() {
        String yearString = converter.convertToDatabaseColumn(Year.parse("2024"));
        assertEquals("2024", yearString);
    }

    @Test
    void shouldConvertStringToYearAttribute() {
        Year year = converter.convertToEntityAttribute("2024");
        assertEquals(Year.parse("2024"), year);
    }
}