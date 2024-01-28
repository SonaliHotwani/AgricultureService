package com.apple.agriculture.domain;

import lombok.Data;

import java.time.Year;
import java.util.List;

@Data
public class FilterCriteria {
    List<Year> years;
    List<String> crops;

    public FilterCriteria(List<Year> years, List<String> crops) {
        this.years = years;
        this.crops = crops;
    }

    public FilterCriteria() {

    }
}
