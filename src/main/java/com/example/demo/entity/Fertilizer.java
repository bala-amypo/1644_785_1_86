
package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String recommendedForCrops;
}    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRecommendedforcrops() { return recommendedForCrops; }
    public void setRecommendedforcrops(String recommendedForCrops) { this.recommendedForCrops = recommendedForCrops; }
