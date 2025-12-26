
package com.example.demo.controller;
import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/farms") @RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<?> createFarm(@RequestBody FarmRequest req, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Farm f = Farm.builder().name(req.getName()).soilPH(req.getSoilPH())
                     .waterLevel(req.getWaterLevel()).season(req.getSeason()).build();
        return ResponseEntity.ok(farmService.createFarm(f, userId));
    }

    @GetMapping
    public ResponseEntity<?> listFarms(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return ResponseEntity.ok(farmService.getFarmsByOwner(userId));
    }
}