import os
from pathlib import Path

BASE = Path("src/main/java/com/example/demo")

files = {
    "DemoApplication.java": """package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
""",

    "entity/User.java": """package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
}
""",

    "entity/Farm.java": """package com.example.demo.entity;

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
    private double soilPH;
    private double waterLevel;
    private String season;

    @ManyToOne
    private User owner;
}
""",

    "entity/Crop.java": """package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private double requiredWater;
    private String season;
}
""",

    "entity/Fertilizer.java": """package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String npkRatio;
    private String recommendedForCrops;
}
""",

    "entity/Suggestion.java": """package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {
    @Id @GeneratedValue
    private Long id;
    private String suggestedCrops;
    private String suggestedFertilizers;
    private LocalDateTime createdAt;

    @ManyToOne
    private Farm farm;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
""",

    "util/ValidationUtil.java": """package com.example.demo.util;

public class ValidationUtil {
    public static boolean validSeason(String s) {
        return s != null && (s.equals("Kharif") || s.equals("Rabi") || s.equals("Zaid"));
    }
}
""",

    "exception/BadRequestException.java": """package com.example.demo.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
""",

    "exception/ResourceNotFoundException.java": """package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
""",

    "exception/GlobalExceptionHandler.java": """package com.example.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
""",

    "security/JwtTokenProvider.java": """package com.example.demo.security;

import io.jsonwebtoken.*;

public class JwtTokenProvider {

    private final String secret = "secret";

    public String createToken(Long id, String email, String role) {
        return Jwts.builder()
                .claim("id", id)
                .claim("email", email)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get("email", String.class);
    }

    public Long getUserId(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get("id", Long.class);
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get("role", String.class);
    }
}
""",

    "security/JwtAuthenticationFilter.java": """package com.example.demo.security;

public class JwtAuthenticationFilter {
    public JwtAuthenticationFilter(JwtTokenProvider provider) {}
}
""",

    "config/SwaggerConfig.java": """package com.example.demo.config;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
"""
}

def main():
    for rel_path, content in files.items():
        file_path = BASE / rel_path
        os.makedirs(file_path.parent, exist_ok=True)
        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Created: {file_path}")

    print("\n✅ Project structure and files generated successfully.")

if __name__ == "__main__":
    main()
