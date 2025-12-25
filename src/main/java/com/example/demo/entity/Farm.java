
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
    private String farmName;
    private double soilPH;
    private double soilPh;
    private double waterLevel;
    private String season;
    private String Location;
    public double getSoilPh(){return this.soilPh;}

    @ManyToOne
    private User user;
}
