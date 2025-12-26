package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    // Manual Constructor (Replaces Lombok)
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/crop")
    public ResponseEntity<Crop> addCrop(@RequestBody CropRequest req, Authentication auth) {
        // Test case check for ADMIN role
        if (auth == null || !auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        Crop crop = Crop.builder()
                .name(req.getName())
                .suitablePHMin(req.getSuitablePHMin())
                .suitablePHMax(req.getSuitablePHMax())
                .requiredWater(req.getRequiredWater())
                .season(req.getSeason())
                .build();

        return ResponseEntity.ok(catalogService.addCrop(crop));
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<Fertilizer> addFertilizer(@RequestBody FertilizerRequest req, Authentication auth) {
        if (auth == null || !auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
            return ResponseEntity.status(403).build();
        }

        Fertilizer fert = Fertilizer.builder()
                .name(req.getName())
                .npkRatio(req.getNpkRatio())
                .recommendedForCrops(req.getRecommendedForCrops())
                .build();

        return ResponseEntity.ok(catalogService.addFertilizer(fert));
    }
}