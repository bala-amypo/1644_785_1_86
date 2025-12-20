package com.example.demo.service;

import com.example.demo.entity.Userentity;

import java.util.List;

public interface Userservice {

    Userentity create(Userentity user);

    Userentity getById(Long id);

    List<User> getAll();

    Userentity update(Long id, Userentity user);

    void delete(Long id);
}