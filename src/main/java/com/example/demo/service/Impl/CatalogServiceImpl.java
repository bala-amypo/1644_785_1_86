package com.example.demo.service;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;

    public CatalogServiceImpl(CropRepository cropRepository,
                              FertilizerRepository fertilizerRepository) {
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Crop addCrop(Crop crop) {

        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }

        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min cannot be greater than PH max");
        }

        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {

        if (!fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("Invalid NPK format");
        }

        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(double soilPH, String season) {
        return cropRepository.findSuitableCrops(soilPH, season);
    }

    @Override
    public List<Crop> findSuitableCrops(double soilPH, double waterLevel, String season) {
        return cropRepository.findSuitableCropsAdvanced(soilPH, waterLevel, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {
        if (cropNames == null || cropNames.isEmpty()) {
            return List.of();
        }
        return fertilizerRepository.findByCropNames(cropNames);
    }
}
