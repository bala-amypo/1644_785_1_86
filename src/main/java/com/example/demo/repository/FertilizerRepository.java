package com.example.demo.repository;
import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    @Query("SELECT f FROM Fertilizer f WHERE f.recommendedForCrops LIKE %?1%")
    List<Fertilizer> findByCropName(String cropName);
}