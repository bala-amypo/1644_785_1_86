package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CatalogServiceImpl implements CatalogService {
    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public CatalogServiceImpl(CropRepository cropRepo, FertilizerRepository fertRepo) {
        this.cropRepo = cropRepo;
        this.fertRepo = fertRepo;
    }

    @Override
    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) throw new BadRequestException("PH min must be <= PH max");
        if (!ValidationUtil.validSeason(crop.getSeason())) throw new BadRequestException("Invalid season");
        return cropRepo.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if (!fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+")) throw new BadRequestException("Invalid NPK format");
        return fertRepo.save(fertilizer);
    }

    @Override public List<Crop> findSuitableCrops(Double ph, Double water, String season) { return cropRepo.findSuitableCrops(ph, water, season); }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        Set<Fertilizer> ferts = new HashSet<>();
        for (String name : cropNames) { ferts.addAll(fertRepo.findByCropName(name)); }
        return new ArrayList<>(ferts);
    }
}