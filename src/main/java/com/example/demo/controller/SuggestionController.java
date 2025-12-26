
package com.example.demo.controller;

import com.example.demo.service.SuggestionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    public String generate(long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }

    public String getSuggestion(long farmId) {
        return suggestionService.getSuggestion(farmId);
    }


    @PostMapping("/{farmId}")
public ResponseEntity<Suggestion> generate(@PathVariable Long farmId) {
    // Return Suggestion object, NOT String
    return ResponseEntity.ok(suggestionService.generateSuggestion(farmId));
}

@GetMapping("/{suggestionId}")
public ResponseEntity<Suggestion> getSuggestion(@PathVariable Long suggestionId) {
    return ResponseEntity.ok(suggestionService.getSuggestion(suggestionId));
}
}
