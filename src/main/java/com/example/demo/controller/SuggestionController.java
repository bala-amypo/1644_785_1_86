package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://9057.408procr.amypo.ai") 
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping
    public Suggestion createSuggestion(@RequestBody Suggestion suggestion) {
        return suggestionService.saveSuggestion(suggestion);
    }

    @GetMapping
    public List<Suggestion> getAllSuggestions() {
        return suggestionService.getAllSuggestions();
    }

    @GetMapping("/{id}")
    public Suggestion getSuggestionById(@PathVariable Long id) {
        return suggestionService.getSuggestionById(id);
    }
}