package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double soilPh;
    private double waterLevel;
    private String season;
    private String location;
    private String farmName;

    @ManyToOne
    private User user;
}
