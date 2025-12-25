
package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmController {

    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService farmService, UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }

    public Farm createFarm(FarmRequest req, Authentication auth) {
        Farm farm = new Farm();
        farm.setName(req.getName());
        return farmService.createFarm(farm, 1L);
    }

    public Object listFarms(Authentication auth) {
        return farmService.getFarmsByOwner(1L);
    }
}
