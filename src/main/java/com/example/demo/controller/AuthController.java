
package com.example.demo.controller;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        User u = User.builder().name(req.getName()).email(req.getEmail()).password(req.getPassword()).build();
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        User u = userService.findByEmail(req.getEmail());
        if(u != null && new BCryptPasswordEncoder().matches(req.getPassword(), u.getPassword())) {
            String token = jwtTokenProvider.createToken(u.getId(), u.getEmail(), u.getRole());
            return ResponseEntity.ok(AuthResponse.builder().token(token).userId(u.getId()).email(u.getEmail()).role(u.getRole()).build());
        }
        return ResponseEntity.status(401).build();
    }
}
