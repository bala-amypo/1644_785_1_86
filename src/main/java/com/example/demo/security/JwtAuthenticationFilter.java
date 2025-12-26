
package com.example.demo.security;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private String secret = "secretsecretsecretsecretsecretsecretsecretsecret";

    public String createToken(Long userId, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String token) {
        try { Jwts.parser().setSigningKey(secret).parseClaimsJws(token); return true; } catch (Exception e) { return false; }
    }

    public String getEmail(String token) { return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject(); }
    public Long getUserId(String token) { return ((Number) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("userId")).longValue(); }
    public String getRole(String token) { return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role"); }
}