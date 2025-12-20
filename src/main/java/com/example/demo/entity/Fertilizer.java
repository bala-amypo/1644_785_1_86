package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fertilizers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fertilizerentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;        // Organic / Inorganic
    private String nutrient;    // Nitrogen, Phosphorus, etc.
    private String description;
}