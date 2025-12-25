
package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {

    Farm createFarm(Farm farm, long ownerId);

    List<Farm> getFarmsByOwner(long ownerId);
}
