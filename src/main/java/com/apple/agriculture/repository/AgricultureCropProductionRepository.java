package com.apple.agriculture.repository;

import com.apple.agriculture.domain.AgricultureCropProduction;
import com.apple.agriculture.domain.ProductionByCrop;
import com.apple.agriculture.domain.ProductionByYear;
import com.apple.agriculture.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface AgricultureCropProductionRepository extends JpaRepository<AgricultureCropProduction, Long> {
    @Query("SELECT new com.apple.agriculture.domain.ProductionByYear(data.yearRange, sum (data.production)) from AgricultureCropProduction data where data.state = :state group by data.yearRange")
    List<ProductionByYear> findProductionPerYear(@Param("state") State state);

    @Query("SELECT new com.apple.agriculture.domain.ProductionByCrop(data.crop, sum (data.production)) from AgricultureCropProduction data where data.state = :state group by data.crop")
    List<ProductionByCrop> findProductionPerCrop(@Param("state") State state);

    List<AgricultureCropProduction> findByState(State state);

    List<AgricultureCropProduction> findByStateAndYearRangeInAndCropIn(State state, List<Year> years, List<String> crops);

    List<AgricultureCropProduction> findByStateAndYearRangeIn(State state, List<Year> years);

    List<AgricultureCropProduction> findByStateAndCropIn(State state, List<String> crops);
}
