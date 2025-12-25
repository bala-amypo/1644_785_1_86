
package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm createFarm(Farm farm, long ownerId) {
        farm.setOwnerId(ownerId);
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }
}
