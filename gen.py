import os
from pathlib import Path

BASE_DIR = Path("src/main/java/com/example/demo/dto")

dtos = {
    "AuthRequest.java": """package com.example.demo.dto;

public class AuthRequest {

    private String email;
    private String password;

    public AuthRequest() {}

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
""",

    "AuthResponse.java": """package com.example.demo.dto;

public class AuthResponse {

    private String token;

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
""",

    "RegisterRequest.java": """package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
""",

    "FarmRequest.java": """package com.example.demo.dto;

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
""",

    "CropRequest.java": """package com.example.demo.dto;

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
""",

    "FertilizerRequest.java": """package com.example.demo.dto;

public class FertilizerRequest {

    private String name;
    private String npkRatio;
    private String recommendedForCrops;

    public FertilizerRequest() {}

    public FertilizerRequest(String name, String npkRatio, String recommendedForCrops) {
        this.name = name;
        this.npkRatio = npkRatio;
        this.recommendedForCrops = recommendedForCrops;
    }

    public String getName() {
        return name;
    }

    public String getNpkRatio() {
        return npkRatio;
    }

    public String getRecommendedForCrops() {
        return recommendedForCrops;
    }
}
"""
}

def main():
    os.makedirs(BASE_DIR, exist_ok=True)

    for filename, content in dtos.items():
        file_path = BASE_DIR / filename
        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Created: {file_path}")

    print("\n✅ All DTO files generated successfully.")

if __name__ == "__main__":
    main()
