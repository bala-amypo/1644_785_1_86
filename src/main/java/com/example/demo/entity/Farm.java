
package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    @Id
    @GeneratedValue
    private Long id;
    private String farmName;
    private String ownerId;
}    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFarmname() { return farmName; }
    public void setFarmname(String farmName) { this.farmName = farmName; }
    public String getOwnerid() { return ownerId; }
    public void setOwnerid(String ownerId) { this.ownerId = ownerId; }
