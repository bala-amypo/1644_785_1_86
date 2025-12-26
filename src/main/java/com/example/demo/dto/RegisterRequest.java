package com.example.demo.dto;
import lombok.*;
@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterRequest { private String name, email, password; }