package com.apple.agriculture.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductionByCrop {
    String crop;
    BigDecimal production;

    public ProductionByCrop(String crop, BigDecimal production) {
        this.crop = crop;
        this.production = production;
    }

    public ProductionByCrop() {

    }
}
