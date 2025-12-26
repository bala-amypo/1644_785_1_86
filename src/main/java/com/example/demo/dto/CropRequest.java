package com.example.demo.dto;
import lombok.*;
@Data @AllArgsConstructor @NoArgsConstructor
public class CropRequest { private String name; private Double suitablePHMin, suitablePHMax, requiredWater; private String season; }