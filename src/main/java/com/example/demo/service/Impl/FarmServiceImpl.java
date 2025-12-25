package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    @Override
    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with id " + id));
    }

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public List<Farm> getFarmsByUserId(Long userId) {
        return farmRepository.findByUserId(userId);
    }

    @Override
    public List<Farm> findByUserId(Long userId) {
        return farmRepository.findByUserId(userId);
    }

    @Override
    public Farm updateFarm(Long id, Farm farm) {
        Farm existingFarm = getFarmById(id);

        existingFarm.setFarmName(farm.getFarmName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setSoilPh(farm.getSoilPh());
        existingFarm.setSeason(farm.getSeason());
        existingFarm.setUser(farm.getUser());

        return farmRepository.save(existingFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        Farm farm = getFarmById(id);
        farmRepository.delete(farm);
    }
}