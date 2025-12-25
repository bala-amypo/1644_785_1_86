package com.example.demo.repository; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.Crop; public interface CropRepository extends JpaRepository<Crop,Long>{ 
    List<Crop> findSuitableCrops(double soilPH, String season);

List<Crop> findSuitableCropsAdvanced(double soilPH, double waterLevel, String season);

    } 