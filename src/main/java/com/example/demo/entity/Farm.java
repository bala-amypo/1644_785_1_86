
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Farm {

    @Id
    @GeneratedValue
    private Long id;

    private String farmName;
    private String location;
    private String season;
    private Long ownerId;
}
