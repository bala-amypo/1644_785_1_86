
package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public Suggestion generateSuggestion(Long farmId) {
        Farm f = farmService.getFarmById(farmId);
        List<Crop> crops = catalogService.findSuitableCrops(f.getSoilPH(), f.getWaterLevel(), f.getSeason());
        List<String> cropNames = crops.stream().map(Crop::getName).collect(Collectors.toList());
        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);
        
        Suggestion s = Suggestion.builder()
                .farm(f)
                .suggestedCrops(String.join(",", cropNames))
                .suggestedFertilizers(ferts.stream().map(Fertilizer::getName).collect(Collectors.joining(",")))
                .build();
        return repo.save(s);
    }
    public Suggestion getSuggestion(Long id) { return repo.findById(id).orElse(null); }
    public List<Suggestion> getSuggestionsByFarm(Long id) { return repo.findByFarmId(id); }
}