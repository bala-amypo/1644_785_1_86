package com.example.demo.repository;
import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
    @Query("SELECT c FROM Crop c WHERE c.season = :season AND :ph BETWEEN c.suitablePHMin AND c.suitablePHMax AND c.requiredWater <= :water")
    List<Crop> findSuitableCrops(Double ph, Double water, String season);
}