package com.example.demo.repository; 
import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.demo.entity.Farm; 
 public interface FarmRepository extends JpaRepository<Farm,Long>{}