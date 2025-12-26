package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suggestions")
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne @JoinColumn(name = "farm_id")
    private Farm farm;
    
    private String suggestedCrops;
    private String suggestedFertilizers;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() { this.createdAt = LocalDateTime.now(); }

    public Suggestion() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public static SuggestionBuilder builder() { return new SuggestionBuilder(); }
    public static class SuggestionBuilder {
        private Long id; private Farm farm; private String suggestedCrops; private String suggestedFertilizers;
        public SuggestionBuilder id(Long id) { this.id = id; return this; }
        public SuggestionBuilder farm(Farm farm) { this.farm = farm; return this; }
        public SuggestionBuilder suggestedCrops(String c) { this.suggestedCrops = c; return this; }
        public SuggestionBuilder suggestedFertilizers(String f) { this.suggestedFertilizers = f; return this; }
        public Suggestion build() { return new Suggestion(id, farm, suggestedCrops, suggestedFertilizers, LocalDateTime.now()); }
    }
}