package com.example.demo.controller;

import com.example.demo.entity.Fertilizer;
import com.example.demo.service.FertilizerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilizers")
@CrossOrigin(origins = "https://9057.408procr.amypo.ai/") 
public class FertilizerController {

    private final FertilizerService fertilizerService;

    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @PostMapping
    public Fertilizer addFertilizer(@RequestBody Fertilizer fertilizer) {
        return fertilizerService.addFertilizer(fertilizer);
    }

    @GetMapping
    public List<Fertilizer> getAllFertilizers() {
        return fertilizerService.getAllFertilizers();
    }

    @GetMapping("/{id}")
    public Fertilizer getFertilizerById(@PathVariable Long id) {
        return fertilizerService.getFertilizerById(id);
    }
}