package com.example.demo.dto;
import lombok.*;
@Data @AllArgsConstructor @NoArgsConstructor
public class FarmRequest { private String name; private Double soilPH, waterLevel; private String season; }