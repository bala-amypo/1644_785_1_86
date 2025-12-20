package com.example.demo.service;

import com.example.demo.entity.Userentity;

import java.util.List;

public interface Userservice {

    Userentity create(User user);

    Userentity getById(Long id);

    List<User> getAll();

    User update(Long id, User user);

    void delete(Long id);
}