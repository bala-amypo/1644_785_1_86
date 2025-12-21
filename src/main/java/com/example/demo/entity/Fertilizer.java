package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fertilizers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;        
    private String nutrient;    
    private String description;
}