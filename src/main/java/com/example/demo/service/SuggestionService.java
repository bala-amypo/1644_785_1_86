package com.example.demo.service;

import com.example.demo.entity.Suggestionentity;

import java.util.List;

public interface Suggestionservice {

    Suggestionentity saveSuggestion(Suggestionentity suggestion);

    List<Suggestion> getAllSuggestions();

    Suggestionentity getSuggestionById(Long id);
}