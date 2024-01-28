package com.apple.agriculture.controller;

import com.apple.agriculture.domain.*;
import com.apple.agriculture.service.AgricultureCropProductionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/agriculture")
@CrossOrigin(origins = "http://localhost:3000")
public class AgricultureCropProductionController {

    private final AgricultureCropProductionService service;
    Logger logger = LoggerFactory.getLogger(AgricultureCropProductionController.class);

    public AgricultureCropProductionController(AgricultureCropProductionService service) {
        this.service = service;
    }

    @GetMapping("/crop-production/state/{state}")
    public List<AgricultureCropProduction> getProductionDataFor(@PathVariable("state") State state) {
        logger.debug("Request received to fetch all crop production data");
        return service.getAllProductionDataFor(state);
    }

    @PostMapping("/crop-production/state/{state}")
    public List<AgricultureCropProduction> createSearchCriteriaResultSetFor(@PathVariable("state") State state, @RequestBody FilterCriteria filterCriteria) {
        logger.debug("Request received to return search criteria result set");
        return service.createSearchCriteriaResultSetFor(state, filterCriteria);
    }

    @GetMapping("/crop-production")
    public Page<AgricultureCropProduction> getProductionData(
            @PageableDefault(size = 20, sort = {"yearRange"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        logger.debug("Request received to fetch all crop production data");
        return service.getAllProductionData(pageable);
    }

    @GetMapping("/crop-production/by-year/state/{state}")
    public List<ProductionByYear> getProductionDataGroupByYear(@PathVariable("state") State state) {
        logger.debug("Request received to fetch crop production data group by year");
        return service.findProductionPerYear(state);
    }

    @GetMapping("/crop-production/by-crop/state/{state}")
    public List<ProductionByCrop> getProductionDataGroupByCrop(@PathVariable("state") State state) {
        logger.debug("Request received to fetch crop production data group by crop");
        return service.findProductionPerCrop(state);
    }
}
