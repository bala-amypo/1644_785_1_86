package com.example.demo.service.Impl;

import com.example.demo.entity.Farmentity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.Farmrepository;
import com.example.demo.service.Farmservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Farmserviceimpl implements Farmservice {

    private final Farmrepository service;

    @Override
    public Farm createFarm(Farmentity farm) {
        return service.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with id " + id));
    }

    @Override
    public List<Farm> getAllFarms() {
        return service.findAll();
    }

    @Override
    public List<Farm> getFarmsByUserId(Long userId) {
        return service.findByUserId(userId);
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
        service.delete(farm);
    }
}

