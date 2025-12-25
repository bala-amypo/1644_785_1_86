
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fertilizer {

    @Id
    @GeneratedValue
    private Long id;

    private String npkRatio;
}
