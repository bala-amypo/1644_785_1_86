package com.example.demo.repository;

import com.example.demo.entity.Suggestionentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Suggestionrepository extends JpaRepository<Suggestion, Long> {
}