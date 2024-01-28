package com.apple.agriculture.service;

import com.apple.agriculture.domain.ProductionUnit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductionConverter {
    public BigDecimal getProductionInTonnes(BigDecimal production, ProductionUnit productionUnit) {
        return switch (productionUnit) {
            case Nuts -> production.multiply(BigDecimal.valueOf(1.4)).multiply(BigDecimal.valueOf(0.001)).stripTrailingZeros();
            case Tonnes -> production;
            case Bales -> production.multiply(BigDecimal.valueOf(0.181436948)).stripTrailingZeros();
        };
    }
}
