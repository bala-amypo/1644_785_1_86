package com.example.demo.dto;

public class CropRequest {
    private String name;
    private Double suitablePHMin;
    private Double suitablePHMax;
    private Double requiredWater;
    private String season;

    public CropRequest() {}

    // GETTERS ARE MANDATORY WITHOUT LOMBOK
    public String getName() { return name; }
    public Double getSuitablePHMin() { return suitablePHMin; }
    public Double getSuitablePHMax() { return suitablePHMax; }
    public Double getRequiredWater() { return requiredWater; }
    public String getSeason() { return season; }

    // SETTERS (Needed for Spring to fill the object from JSON)
    public void setName(String name) { this.name = name; }
    public void setSuitablePHMin(Double suitablePHMin) { this.suitablePHMin = suitablePHMin; }
    public void setSuitablePHMax(Double suitablePHMax) { this.suitablePHMax = suitablePHMax; }
    public void setRequiredWater(Double requiredWater) { this.requiredWater = requiredWater; }
    public void setSeason(String season) { this.season = season; }
}