
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
}
