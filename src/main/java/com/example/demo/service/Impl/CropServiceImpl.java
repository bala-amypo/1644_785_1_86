package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CropRepository;
import com.example.demo.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;

    @Override
    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public Crop getCropById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id " + id));
    }

    @Override
    public void deleteCrop(Long id) {
        Crop crop = getCropById(id);
        cropRepository.delete(crop);
    }
}