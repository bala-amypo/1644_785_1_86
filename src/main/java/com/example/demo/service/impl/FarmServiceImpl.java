
package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository repo;

    public FarmServiceImpl(FarmRepository repo) {
        this.repo = repo;
    }

    public Farm createFarm(Farm farm, long ownerId) {
        farm.setOwnerId(ownerId);
        return repo.save(farm);
    }

    public List<Farm> getFarmsByOwner(long ownerId) {
        return repo.findByOwnerId(ownerId);
    }
}
