package com.apple.agriculture.repository;

import com.apple.agriculture.domain.AgricultureCropProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultureCropProductionRepository extends JpaRepository<AgricultureCropProduction, Long> {
}
