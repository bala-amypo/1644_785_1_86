package com.example.demo.service;

import com.example.demo.entity.Suggestion;

import java.util.List;

public interface SuggestionService {

    Suggestion saveSuggestion(Suggestion suggestion);

    List<Suggestion> getAllSuggestions();

    Suggestion getSuggestionById(Long id);
}