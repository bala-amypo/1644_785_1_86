package com.example.demo.entity;

import lombok.*; // THIS MUST BE PRESENT
import javax.persistence.*;

@Entity
@Data // Generates Getters/Setters
@Builder // Generates Builder
@NoArgsConstructor // Generates default constructor
@AllArgsConstructor // Generates all-args constructor
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "farm_id")
    private Farm farm;
    private String suggestedCrops;
    private String suggestedFertilizers;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}