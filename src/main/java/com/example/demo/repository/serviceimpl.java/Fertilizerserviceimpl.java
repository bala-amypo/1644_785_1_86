package com.example.demo.service.impl;

import com.example.demo.entity.Fertilizerentity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.Fertilizerservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Fertilizerserviceimpl implements Fertilizerservice {

    private final FertilizerRepository fertilizerRepository;

    public Fertilizerserviceimpl(Fertilizerrepository fertilizerRepository) {
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Fertilizer> getAllFertilizers() {
        return fertilizerRepository.findAll();
    }

    @Override
    public Fertilizer getFertilizerById(Long id) {
        return fertilizerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fertilizer not found with id " + id));
    }
}