package com.apple.agriculture.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;

@Data
public class ProductionByYear {
    Year yearRange;
    BigDecimal production;

    public ProductionByYear(Year yearRange, BigDecimal production) {
        this.yearRange = yearRange;
        this.production = production;
    }
    public ProductionByYear() {

    }
}
