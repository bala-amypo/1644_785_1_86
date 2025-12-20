package com.example.demo.service.impl;

import com.example.demo.entity.Suggestionentity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.Suggestionrepository;
import com.example.demo.service.Suggestionservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Suggestionserviceimpl implements Suggestionservice {

    private final Suggestionrepository suggestionrepository;

    @Override
    public Suggestion saveSuggestion(Suggestion suggestion) {
        return service.save(suggestion);
    }

    @Override
    public List<Suggestion> getAllSuggestions() {
        return service.findAll();
    }

    @Override
    public Suggestion getSuggestionById(Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found with id " + id));
    }
}
