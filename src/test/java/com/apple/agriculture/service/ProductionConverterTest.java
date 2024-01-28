package com.apple.agriculture.service;

import com.apple.agriculture.domain.ProductionUnit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductionConverterTest {

    private final ProductionConverter converter = new ProductionConverter();

    @Test
    void shouldConvertNutsToTonnes() {
        BigDecimal productionInTonnes = converter.getProductionInTonnes(BigDecimal.valueOf(986000.0), ProductionUnit.Nuts);
        assertEquals(BigDecimal.valueOf(1380.4), productionInTonnes);
    }

    @Test
    void shouldConvertBalesToTonnes() {
        BigDecimal productionInTonnes = converter.getProductionInTonnes(BigDecimal.valueOf(4.0), ProductionUnit.Bales);
        assertEquals(BigDecimal.valueOf(0.725747792), productionInTonnes);
    }
}