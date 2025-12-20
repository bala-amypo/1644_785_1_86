package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping
    public Crop createCrop(@RequestBody Crop crop) {
        return cropService.saveCrop(crop);
    }

    @GetMapping
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/{id}")
    public Crop getCropById(@PathVariable Long id) {
        return cropService.getCropById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return "Crop deleted successfully";
    }
}