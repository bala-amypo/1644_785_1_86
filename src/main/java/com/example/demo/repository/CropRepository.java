package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findSuitableCrops(double soilPh, String season);

List<Crop> findSuitableCropsAdvanced(double soilPh, double waterLevel, String season);

}