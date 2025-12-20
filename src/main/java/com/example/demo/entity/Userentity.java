package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    
    private String email;

    private String password;

    @Builder.Default
    private String role = "USER";
    }