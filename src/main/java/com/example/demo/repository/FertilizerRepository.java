package com.example.demo.repository;

import com.example.demo.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    List<Fertilizer> findByCropName(String cropName);

List<Fertilizer> findByCropNames(List<String> cropNames);

}