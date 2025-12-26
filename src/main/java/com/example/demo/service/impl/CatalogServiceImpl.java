
package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public Crop addCrop(Crop crop) {
        if(crop.getSuitablePHMin() > crop.getSuitablePHMax()) throw new BadRequestException("PH min > max");
        if(!ValidationUtil.validSeason(crop.getSeason())) throw new BadRequestException("Invalid season");
        return cropRepo.save(crop);
    }
    public Fertilizer addFertilizer(Fertilizer f) {
        if(!f.getNpkRatio().matches("\\d+-\\d+-\\d+")) throw new BadRequestException("Invalid NPK");
        return fertRepo.save(f);
    }
    public List<Crop> findSuitableCrops(Double ph, Double water, String season) { return cropRepo.findSuitableCrops(ph, season); }
    public List<Fertilizer> findFertilizersForCrops(List<String> names) {
        Set<Fertilizer> res = new HashSet<>();
        for(String n : names) res.addAll(fertRepo.findByCropName(n));
        return new ArrayList<>(res);
    }
}
