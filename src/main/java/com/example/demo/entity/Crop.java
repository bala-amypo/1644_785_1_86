
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Crop {

    @Id
    @GeneratedValue
    private Long id;

    private String season;
    private double suitablePHMin;
    private double suitablePHMax;
}
