import os
from pathlib import Path

BASE = Path("src/main/java/com/example/demo")

def write(path, content):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8")
    print("Created:", path)

# -------------------- DemoApplication --------------------
write(BASE / "DemoApplication.java", """
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
""")

# -------------------- EXCEPTIONS --------------------
write(BASE / "exception/BadRequestException.java", """
package com.example.demo.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) { super(msg); }
}
""")

write(BASE / "exception/ResourceNotFoundException.java", """
package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) { super(msg); }
}
""")

write(BASE / "exception/GlobalExceptionHandler.java", """
package com.example.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
""")

# -------------------- UTIL --------------------
write(BASE / "util/ValidationUtil.java", """
package com.example.demo.util;

import java.util.Set;

public class ValidationUtil {
    private static final Set<String> SEASONS = Set.of("Kharif", "Rabi");

    public static boolean validSeason(String s) {
        return SEASONS.contains(s);
    }
}
""")

# -------------------- ENTITIES --------------------
entity_common = """
import jakarta.persistence.*;
import lombok.*;
"""

write(BASE / "entity/User.java", f"""
package com.example.demo.entity;
{entity_common}

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}}
""")

write(BASE / "entity/Farm.java", f"""
package com.example.demo.entity;
{entity_common}

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double soilPH;
    private double waterLevel;
    private String season;

    @ManyToOne
    private User owner;
}}
""")

write(BASE / "entity/Crop.java", f"""
package com.example.demo.entity;
{entity_common}

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crop {{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private double requiredWater;
    private String season;
}}
""")

write(BASE / "entity/Fertilizer.java", f"""
package com.example.demo.entity;
{entity_common}

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fertilizer {{
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String npkRatio;
    private String recommendedForCrops;
}}
""")

write(BASE / "entity/Suggestion.java", f"""
package com.example.demo.entity;
{entity_common}

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {{
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Farm farm;

    private String suggestedCrops;
    private String suggestedFertilizers;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {{
        createdAt = LocalDateTime.now();
    }}
}}
""")

# -------------------- DTOs --------------------
dto = BASE / "dto"
write(dto / "AuthRequest.java", "package com.example.demo.dto; public record AuthRequest(String email,String password){}")
write(dto / "AuthResponse.java", "package com.example.demo.dto; public record AuthResponse(String token){}")
write(dto / "RegisterRequest.java", "package com.example.demo.dto; public record RegisterRequest(String name,String email,String password){}")
write(dto / "FarmRequest.java", "package com.example.demo.dto; public record FarmRequest(String name,double soilPH,double waterLevel,String season){}")
write(dto / "CropRequest.java", "package com.example.demo.dto; public record CropRequest(String name,double suitablePHMin,double suitablePHMax,double requiredWater,String season){}")
write(dto / "FertilizerRequest.java", "package com.example.demo.dto; public record FertilizerRequest(String name,String npkRatio,String recommendedForCrops){}")

# -------------------- REPOSITORIES --------------------
repo = BASE / "repository"
write(repo / "UserRepository.java", "package com.example.demo.repository; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.User; public interface UserRepository extends JpaRepository<User,Long>{ Optional<User> findByEmail(String e);} ")
write(repo / "FarmRepository.java", "package com.example.demo.repository; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.Farm; public interface FarmRepository extends JpaRepository<Farm,Long>{}")
write(repo / "CropRepository.java", "package com.example.demo.repository; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.Crop; public interface CropRepository extends JpaRepository<Crop,Long>{ List<Crop> findSuitableCrops(double ph,String season);} ")
write(repo / "FertilizerRepository.java", "package com.example.demo.repository; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.Fertilizer; public interface FertilizerRepository extends JpaRepository<Fertilizer,Long>{ List<Fertilizer> findByCropName(String name);} ")
write(repo / "SuggestionRepository.java", "package com.example.demo.repository; import java.util.*; import org.springframework.data.jpa.repository.JpaRepository; import com.example.demo.entity.Suggestion; public interface SuggestionRepository extends JpaRepository<Suggestion,Long>{ List<Suggestion> findByFarmId(Long id);} ")

# -------------------- SECURITY --------------------
write(BASE / "security/JwtTokenProvider.java", """
package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret = "secret";
    private final long validity = 3600000;

    public String createToken(Long id, String email, String role) {
        return Jwts.builder()
                .claim("id", id)
                .claim("email", email)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis()+validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    public String getEmail(String token) {
        return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("email");
    }

    public Long getUserId(String token) {
        return ((Number) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("id")).longValue();
    }

    public String getRole(String token) {
        return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role");
    }
}
""")

write(BASE / "security/JwtAuthenticationFilter.java", """
package com.example.demo.security;
public class JwtAuthenticationFilter {
    public JwtAuthenticationFilter(JwtTokenProvider p){}
}
""")



print("\\n✅ PROJECT GENERATION COMPLETE")
