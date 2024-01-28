package com.apple.agriculture.controller;

import com.apple.agriculture.domain.AgricultureCropProduction;
import com.apple.agriculture.domain.FilterCriteria;
import com.apple.agriculture.domain.ProductionByYear;
import com.apple.agriculture.domain.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AgricultureCropProductionControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnProductionData() {
        ResponseEntity<List<AgricultureCropProduction>> responseEntity = testRestTemplate.exchange(URI.create("http://localhost:" + port + "/agriculture/crop-production/state/" + State.MAHARASHTRA), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        List<AgricultureCropProduction> cropProductions = responseEntity.getBody();
        assertEquals(HttpStatus.OK, statusCode);
        assert cropProductions != null;
        assertEquals(5, cropProductions.size());
        assertEquals(State.MAHARASHTRA, cropProductions.get(0).getState());
    }

    @Test
    public void shouldReturnProductionDataByYearForMaharashtraState() {
        String str = "http://localhost:" + port + "/agriculture/crop-production/by-year/state/" + State.MAHARASHTRA;
        ResponseEntity<List<ProductionByYear>> responseEntity = testRestTemplate.exchange(URI.create(str), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        List<ProductionByYear> productionsByYear = responseEntity.getBody();
        assertEquals(HttpStatus.OK, statusCode);
        assert productionsByYear != null;
        assertEquals(4, productionsByYear.size());
        assertEquals(Year.parse("2004"), productionsByYear.get(0).getYearRange());
        assertEquals(BigDecimal.valueOf(125.2), productionsByYear.get(0).getProduction().stripTrailingZeros());
        assertEquals(Year.parse("2005"), productionsByYear.get(1).getYearRange());
        assertEquals(BigDecimal.valueOf(125.2), productionsByYear.get(1).getProduction().stripTrailingZeros());
        assertEquals(Year.parse("2006"), productionsByYear.get(2).getYearRange());
        assertEquals(BigDecimal.valueOf(250.4), productionsByYear.get(2).getProduction().stripTrailingZeros());
        assertEquals(Year.parse("2007"), productionsByYear.get(3).getYearRange());
        assertEquals(BigDecimal.valueOf(125.2), productionsByYear.get(3).getProduction().stripTrailingZeros());
    }

    @Test
    void shouldReturnFilterCriteriaResultSetForGivenState() {
        String str = "http://localhost:" + port + "/agriculture/crop-production/state/" + State.MAHARASHTRA;
        List<String> crops = new ArrayList<>();
        crops.add("Rice");
        crops.add("Wheat");
        List<Year> years = new ArrayList<>();
        years.add(Year.of(2007));
        years.add(Year.of(2006));
        FilterCriteria filterCriteria = new FilterCriteria(years, crops);
        HttpEntity<FilterCriteria> requestEntity = new HttpEntity<>(filterCriteria);
        ResponseEntity<List<AgricultureCropProduction>> responseEntity = testRestTemplate.exchange(URI.create(str), HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {
        });
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        List<AgricultureCropProduction> filteredProductions = responseEntity.getBody();
        assertEquals(HttpStatus.OK, statusCode);
        assert filteredProductions != null;
        assertEquals(3, filteredProductions.size());
    }
}