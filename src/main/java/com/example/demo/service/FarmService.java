package com.example.demo.service;

import com.example.demo.entity.Farm;

import java.util.List;

public interface FarmService {

    Farm createFarm(Farm farm);

    Farm getFarmById(Long id);

    List<Farm> getAllFarms();

    List<Farm> getFarmsByUserId(Long userId);

    Farm updateFarm(Long id, Farm farm);

    void deleteFarm(Long id);
}
