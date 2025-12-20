package com.example.demo.repository;

import com.example.demo.entity.Farmentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Farmrepository extends JpaRepository<Farm, Long> {

    List<Farm> findByUserId(Long userId);
}