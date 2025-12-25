package com.example.demo.repository; 
import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.demo.entity.Farm; 
 import java.util.*;
 public interface FarmRepository extends JpaRepository<Farm,Long>{
    List<Farm> findByUserId(Long userId);
    List<Farm> findByOwnerId(long ownerId);
 }