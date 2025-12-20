package com.example.demo.repository;

import com.example.demo.entity.Cropentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Croprepository extends JpaRepository<Crop, Long> {
}