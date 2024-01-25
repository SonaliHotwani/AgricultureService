package com.apple.agriculture.domain;

import com.apple.agriculture.service.YearToStringConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;


@Data
@Entity
@Table(name = "AGRICULTURE_CROP_PRODUCTION")
public class AgricultureCropProduction {
    @Enumerated(EnumType.STRING)
    @Column(name = "State")
    State state;
    @Column(name = "District")
    String district;
    @Column(name = "Crop")
    String crop;
    @Convert(converter = YearToStringConverter.class)
    @Column(name = "\"Year\"")
    Year yearRange;
    @Column(name = "Season")
    String season;
    @Column(name = "Area")
    BigDecimal area;
    @Enumerated(EnumType.STRING)
    @Column(name = "Area Units")
    AreaUnit areaUnit;
    @Column(name = "Production")
    BigDecimal production;
    @Enumerated(EnumType.STRING)
    @Column(name = "Production Units")
    ProductionUnit productionUnit;
    @Column(name = "Yield")
    BigDecimal yield;
    @Column(name = "\"Id\"")
    @Id
    @SequenceGenerator(name = "ID_GEN", sequenceName = "ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN")
    private Long id;
}
