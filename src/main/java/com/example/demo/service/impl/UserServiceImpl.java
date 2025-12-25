
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) { return repo.save(user); }
    public User getById(Long id) { return repo.findById(id).orElse(null); }
    public List<User> getAll() { return repo.findAll(); }
    public User update(Long id, User user) { return repo.save(user); }
    public void delete(Long id) { repo.deleteById(id); }

    public User register(User user) { return repo.save(user); }
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
