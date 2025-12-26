package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(FarmService farmService, CatalogService catalogService, SuggestionRepository repo) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.repo = repo;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmService.getFarmById(farmId);
        
        // 1. Find Suitable Crops
        List<Crop> crops = catalogService.findSuitableCrops(farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        List<String> cropNames = crops.stream().map(Crop::getName).collect(Collectors.toList());
        
        // 2. Find Fertilizers based on those Crop names
        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);
        List<String> fertNames = ferts.stream().map(Fertilizer::getName).collect(Collectors.toList());

        // 3. Join names into comma-separated strings
        String suggestedCrops = String.join(",", cropNames);
        String suggestedFertilizers = String.join(",", fertNames);

        // 4. Build Suggestion manually (No Lombok)
        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(suggestedCrops)
                .suggestedFertilizers(suggestedFertilizers)
                .build();
                
        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long id) {
        return repo.findByFarmId(id);
    }
}