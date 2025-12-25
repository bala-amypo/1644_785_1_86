
package com.example.demo.service.impl;

import com.example.demo.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {

    @Override
    public String generateSuggestion(long farmId) {
        return "Generated suggestion for farm " + farmId;
    }

    @Override
    public String getSuggestion(long farmId) {
        return "Suggestion for farm " + farmId;
    }
}
