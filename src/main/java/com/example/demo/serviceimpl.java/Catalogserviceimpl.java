package com.example.demo.service.impl;

import com.example.demo.entity.Cropentity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.Croprepository;
import com.example.demo.service.Cropservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Cropserviceimpl implements Cropservice {

    private final Croprepository service;

    @Override
    public Crop saveCrop(Cropentity crop) {
        return service.save(crop);
    }

    @Override
    public List<Crop> getAllCrops() {
        return service.findAll();
    }

    @Override
    public Crop getCropById(Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id " + id));
    }

    @Override
    public void deleteCrop(Long id) {
        Crop crop = getCropById(id);
        cropRepository.delete(crop);
    }
}