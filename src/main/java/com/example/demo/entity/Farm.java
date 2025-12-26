package com.example.demo.entity;

import lombok.*; // THIS MUST BE PRESENT
import javax.persistence.*;

@Entity
@Data // Generates Getters/Setters
@Builder // Generates Builder
@NoArgsConstructor // Generates default constructor
@AllArgsConstructor // Generates all-args constructor
public class Farm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id")
    private User owner;
    private String name;
    private Double soilPH;
    private Double waterLevel;
    private String season;
}