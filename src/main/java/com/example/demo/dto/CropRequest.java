package com.example.demo.dto;

public class CropRequest {

    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private double requiredWater;
    private String season;

    public CropRequest() {}

    public CropRequest(String name,
                       double suitablePHMin,
                       double suitablePHMax,
                       double requiredWater,
                       String season) {
        this.name = name;
        this.suitablePHMin = suitablePHMin;
        this.suitablePHMax = suitablePHMax;
        this.requiredWater = requiredWater;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public double getSuitablePHMin() {
        return suitablePHMin;
    }

    public double getSuitablePHMax() {
        return suitablePHMax;
    }

    public double getRequiredWater() {
        return requiredWater;
    }

    public String getSeason() {
        return season;
    }
}
