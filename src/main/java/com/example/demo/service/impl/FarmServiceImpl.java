
package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.FarmService;
import com.example.demo.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepo;
    private final UserRepository userRepo;

    public Farm createFarm(Farm farm, Long ownerId) {
        User owner = userRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(farm.getSoilPH() < 3 || farm.getSoilPH() > 10) throw new IllegalArgumentException("Invalid pH");
        if(!ValidationUtil.validSeason(farm.getSeason())) throw new BadRequestException("Invalid season");
        farm.setOwner(owner);
        return farmRepo.save(farm);
    }
    public List<Farm> getFarmsByOwner(Long id) { return farmRepo.findByOwnerId(id); }
    public Farm getFarmById(Long id) { return farmRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Farm not found")); }
}