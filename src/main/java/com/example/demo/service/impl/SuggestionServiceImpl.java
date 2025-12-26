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
        
        // Find crops based on farm data
        List<Crop> crops = catalogService.findSuitableCrops(farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());
        
        // Extract names
        List<String> cropNames = crops.stream().map(Crop::getName).collect(Collectors.toList());
        
        // Find fertilizers for those crops
        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);
        List<String> fertNames = ferts.stream().map(Fertilizer::getName).collect(Collectors.toList());

        // Create and Save
        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(String.join(",", cropNames)) // Ensure this isn't empty if crops exist
                .suggestedFertilizers(String.join(",", fertNames))
                .build();
                
        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Suggestion not found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long id) {
        return repo.findByFarmId(id);
    }
}