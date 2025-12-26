
package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) throw new BadRequestException("Email already exists");
        if(user.getRole() == null) user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findByEmail(String email) { return userRepository.findByEmail(email).orElse(null); }
    public User findById(Long id) { return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found")); }
}
