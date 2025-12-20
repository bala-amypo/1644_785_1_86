package com.example.demo.service;

import com.example.demo.entity.Farmentity;

import java.util.List;

public interface Farmservice {

    Farmentity createFarm(Farmentity farm);

    Farmentity getFarmById(Long id);

    List<Farm> getAllFarms();

    List<Farm> getFarmsByUserId(Long userId);

    Farmentity updateFarm(Long id, Farmentity farm);

    void deleteFarm(Long id);
}