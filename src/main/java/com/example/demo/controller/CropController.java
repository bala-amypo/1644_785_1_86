package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/catalog/crops")
public class CropController {

    private final CatalogService catalogService;

    // REMOVED @RequiredArgsConstructor
    // ADDED Manual Constructor for Dependency Injection
    public CropController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/suitable")
    public ResponseEntity<List<Crop>> findCrops(
            @RequestParam Double ph, 
            @RequestParam Double water, 
            @RequestParam String season) {
        return ResponseEntity.ok(catalogService.findSuitableCrops(ph, water, season));
    }
}