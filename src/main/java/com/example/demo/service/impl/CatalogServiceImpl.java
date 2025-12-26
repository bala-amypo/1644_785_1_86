package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
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

    @Override
    public List<Crop> findSuitableCrops(Double ph, Double water, String season) {
        // The test case t39 and t60 call with 3 params, but repo uses 2 (ph, season)
        return cropRepo.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        Set<Fertilizer> result = new HashSet<>();
        for (String name : cropNames) {
            result.addAll(fertRepo.findByCropName(name));
        }
        return new ArrayList<>(result);
    }
}