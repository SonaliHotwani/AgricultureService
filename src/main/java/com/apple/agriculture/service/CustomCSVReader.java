package com.apple.agriculture.service;

import com.apple.agriculture.domain.AgricultureCropProduction;
import com.apple.agriculture.domain.AreaUnit;
import com.apple.agriculture.domain.ProductionUnit;
import com.apple.agriculture.domain.State;
import com.apple.agriculture.repository.AgricultureCropProductionRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomCSVReader {

    private final AgricultureCropProductionRepository repository;
    private final ProductionConverter productionConverter;

    public CustomCSVReader(AgricultureCropProductionRepository repository, ProductionConverter productionConverter) {
        this.repository = repository;
        this.productionConverter = productionConverter;
    }

    public void readAndLoadInDB(String filePath) throws SQLException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();
            List<AgricultureCropProduction> cropProductions = new ArrayList<>();
            String[] line;
            while ((line = reader.readNext()) != null) {
                AgricultureCropProduction agricultureCropProduction = new AgricultureCropProduction();
                agricultureCropProduction.setState(State.getByValue(line[0]));
                agricultureCropProduction.setDistrict(line[1]);
                agricultureCropProduction.setCrop(line[2]);
                agricultureCropProduction.setYearRange(Year.parse(line[3].split("-")[0]));
                agricultureCropProduction.setSeason(line[4]);
                BigDecimal area = StringUtils.isEmpty(line[5]) ? BigDecimal.valueOf(0) : new BigDecimal(line[5]);
                agricultureCropProduction.setArea(area);
                agricultureCropProduction.setAreaUnit(AreaUnit.valueOf(line[6]));
                BigDecimal production = StringUtils.isEmpty(line[7]) ? BigDecimal.valueOf(0) : new BigDecimal(line[7]);
                ProductionUnit productionUnit = ProductionUnit.valueOf(line[8]);
                BigDecimal productionInTonnes = productionConverter.getProductionInTonnes(production, productionUnit);
                agricultureCropProduction.setProduction(productionInTonnes);
                agricultureCropProduction.setProductionUnit(ProductionUnit.Tonnes);
                BigDecimal yield = StringUtils.isEmpty(line[9]) ? BigDecimal.valueOf(0) : new BigDecimal(line[9]);
                agricultureCropProduction.setYield(yield);
                cropProductions.add(agricultureCropProduction);
            }
            repository.saveAll(cropProductions);
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

    }

}
