package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
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

    public Crop addCrop(Crop crop) {
        if(crop.getSuitablePHMin() > crop.getSuitablePHMax()) throw new BadRequestException("PH min must be <= max");
        if(!ValidationUtil.validSeason(crop.getSeason())) throw new BadRequestException("Invalid season");
        return cropRepo.save(crop);
    }

    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        if(!fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+")) throw new BadRequestException("Invalid NPK format");
        return fertRepo.save(fertilizer);
    }

    public List<Crop> findSuitableCrops(Double ph, Double water, String season) {
        return cropRepo.findSuitableCrops(ph, water, season);
    }

    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        Set<Fertilizer> result = new HashSet<>();
        for(String name : cropNames) {
            result.addAll(fertRepo.findByCropName(name));
        }
        return new ArrayList<>(result);
    }
}