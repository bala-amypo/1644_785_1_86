package com.example.demo.service;

import com.example.demo.entity.Cropentity;

import java.util.List;

public interface CropService {

    Crop saveCrop(Crop crop);

    List<Crop> getAllCrops();

    Crop getCropById(Long id);

    void deleteCrop(Long id);
}