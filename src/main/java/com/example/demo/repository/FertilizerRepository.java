package com.example.demo.repository;

import com.example.demo.entity.Fertilizerentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fertilizerrepository extends JpaRepository<Fertilizer, Long> {
}
