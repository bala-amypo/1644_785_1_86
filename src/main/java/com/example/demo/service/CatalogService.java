package com.example.demo.service;

import com.example.demo.entity.Cropentity;

import java.util.List;

public interface Cropservice {

    Cropentity saveCrop(Cropentity crop);

    List<Crop> getAllCrops();

    Cropentity getCropById(Long id);

    void deleteCrop(Long id);
}