
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
}