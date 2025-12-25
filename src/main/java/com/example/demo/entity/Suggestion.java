package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
}    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
