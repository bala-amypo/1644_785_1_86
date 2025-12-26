package com.example.demo.security;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    // 256-bit key requirement
    private String secret = "ThisIsASecretKeyWithOver32CharactersToPassTheSecurityTest123456";

    public String createToken(Long userId, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String token) {
        try { Jwts.parser().setSigningKey(secret).parseClaimsJws(token); return true; } 
        catch (Exception e) { return false; }
    }

    public String getEmail(String token) { return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject(); }
    public Long getUserId(String token) { 
        Object val = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("userId");
        return Long.valueOf(val.toString());
    }
    public String getRole(String token) { return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role").toString(); }
}