package com.apple.agriculture.service;

import com.apple.agriculture.domain.*;
import com.apple.agriculture.repository.AgricultureCropProductionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgricultureCropProductionService {

    private final AgricultureCropProductionRepository repository;

    public AgricultureCropProductionService(AgricultureCropProductionRepository repository) {
        this.repository = repository;
    }

    public List<AgricultureCropProduction> getAllProductionDataFor(State state) {
        return repository.findByState(state);
    }

    public Page<AgricultureCropProduction> getAllProductionData(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<ProductionByYear> findProductionPerYear(State state) {
        return repository.findProductionPerYear(state);
    }

    public List<ProductionByCrop> findProductionPerCrop(State state) {
        return repository.findProductionPerCrop(state);
    }

    public List<AgricultureCropProduction> createSearchCriteriaResultSetFor(State state, FilterCriteria filterCriteria) {
        if (filterCriteria.getYears().isEmpty() && filterCriteria.getCrops().isEmpty())
            return repository.findByState(state);
        else if (filterCriteria.getYears().isEmpty() && !filterCriteria.getCrops().isEmpty())
            return repository.findByStateAndCropIn(state, filterCriteria.getCrops());
        else if (!filterCriteria.getYears().isEmpty() && filterCriteria.getCrops().isEmpty())
            return repository.findByStateAndYearRangeIn(state, filterCriteria.getYears());
        else
            return repository.findByStateAndYearRangeInAndCropIn(state, filterCriteria.getYears(), filterCriteria.getCrops());
    }
}
