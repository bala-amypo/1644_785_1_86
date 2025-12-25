package com.example.demo.repository; import java.util.*; 
import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.demo.entity.User; public interface UserRepository extends JpaRepository<User,Long>{
     Optional<User> findByEmail(String e);
    boolean existsByEmail(String email);
     } 