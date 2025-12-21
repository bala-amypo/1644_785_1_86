package com.example.demo.controller;

import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://9057.408procr.amypo.ai") 
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
        return new ResponseEntity<>(farmService.createFarm(farm), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @GetMapping
    public ResponseEntity<List<Farm>> getAllFarms() {
        return ResponseEntity.ok(farmService.getAllFarms());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Farm>> getFarmsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(farmService.getFarmsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(
            @PathVariable Long id,
            @RequestBody Farm farm) {
        return ResponseEntity.ok(farmService.updateFarm(id, farm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.ok("Farm deleted successfully");
    }
}