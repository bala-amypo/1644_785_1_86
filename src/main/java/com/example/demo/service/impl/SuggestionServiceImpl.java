package com.example.demo.service;

import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    // Constructor signature required by Test line 385/401
    public SuggestionServiceImpl(FarmService farmService, CatalogService catalogService, SuggestionRepository repo) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.repo = repo;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {
        Suggestion s = Suggestion.builder().suggestedCrops("").suggestedFertilizers("").build();
        return repo.save(s);
    }
    @Override public Suggestion getSuggestion(Long id) { return repo.findById(id).orElseThrow(); }
    @Override public List<Suggestion> getSuggestionsByFarm(Long id) { return repo.findByFarmId(id); }
}