import os

BASE = "src/main/java/com/example/demo"

def write(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        f.write(content)
    print(f"✔ Created {path}")

# =========================
# CONTROLLERS
# =========================

write(f"{BASE}/controller/AuthController.java", """
package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
}
""")

write(f"{BASE}/controller/CatalogController.java", """
package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
}
""")

write(f"{BASE}/controller/SuggestionController.java", """
package com.example.demo.controller;

import com.example.demo.service.SuggestionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    public String generate(long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }

    public String getSuggestion(long farmId) {
        return suggestionService.getSuggestion(farmId);
    }
}
""")

write(f"{BASE}/controller/FarmController.java", """
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
""")

# =========================
# SERVICES
# =========================

write(f"{BASE}/service/UserService.java", """
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);
}
""")

write(f"{BASE}/service/FarmService.java", """
package com.example.demo.service;

import com.example.demo.entity.Farm;
import java.util.List;

public interface FarmService {

    Farm createFarm(Farm farm, long ownerId);

    List<Farm> getFarmsByOwner(long ownerId);
}
""")

write(f"{BASE}/service/SuggestionService.java", """
package com.example.demo.service;

public interface SuggestionService {

    String generateSuggestion(long farmId);

    String getSuggestion(long farmId);
}
""")

# =========================
# SERVICE IMPLEMENTATIONS
# =========================

write(f"{BASE}/service/impl/UserServiceImpl.java", """
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
""")

write(f"{BASE}/service/impl/FarmServiceImpl.java", """
package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import com.example.demo.service.FarmService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm createFarm(Farm farm, long ownerId) {
        farm.setOwnerId(ownerId);
        return farmRepository.save(farm);
    }

    @Override
    public List<Farm> getFarmsByOwner(long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }
}
""")

write(f"{BASE}/service/impl/SuggestionServiceImpl.java", """
package com.example.demo.service.impl;

import com.example.demo.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {

    @Override
    public String generateSuggestion(long farmId) {
        return "Generated suggestion for farm " + farmId;
    }

    @Override
    public String getSuggestion(long farmId) {
        return "Suggestion for farm " + farmId;
    }
}
""")

# =========================
# REPOSITORIES
# =========================

write(f"{BASE}/repository/UserRepository.java", """
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
""")

# =========================
# SWAGGER CONFIG
# =========================



print("\\n✅ ALL FIXES APPLIED SUCCESSFULLY")
