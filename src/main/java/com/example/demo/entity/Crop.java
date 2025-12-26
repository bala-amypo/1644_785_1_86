package com.example.demo.entity;

import lombok.*; // THIS MUST BE PRESENT
import javax.persistence.*;

@Entity
@Data // Generates Getters/Setters
@Builder // Generates Builder
@NoArgsConstructor // Generates default constructor
@AllArgsConstructor // Generates all-args constructor
public class Crop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double suitablePHMin;
    private Double suitablePHMax;
    private Double requiredWater;
    private String season;
}

