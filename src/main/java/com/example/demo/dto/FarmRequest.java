package com.example.demo.dto;

public class FarmRequest {

    private String name;
    private double soilPH;
    private double waterLevel;
    private String season;

    public FarmRequest() {}

    public FarmRequest(String name, double soilPH, double waterLevel, String season) {
        this.name = name;
        this.soilPH = soilPH;
        this.waterLevel = waterLevel;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public double getSoilPH() {
        return soilPH;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public String getSeason() {
        return season;
    }
}
