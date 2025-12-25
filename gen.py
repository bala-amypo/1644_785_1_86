import os
import shutil

BASE = "src/main/java/com/example/demo"

def write(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        f.write(content)
    print(f"✔ Fixed {path}")

# =====================================================
# 1. REMOVE DUPLICATE Impl DIRECTORY
# =====================================================
bad_impl = f"{BASE}/service/Impl"
if os.path.exists(bad_impl):
    shutil.rmtree(bad_impl)
    print("✔ Removed duplicate service/Impl directory")

# =====================================================
# 2. FIX DTOs
# =====================================================
write(f"{BASE}/dto/FarmRequest.java", """
package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmRequest {
    private String name;
}
""")

# =====================================================
# 3. FIX ENTITIES (Lombok)
# =====================================================
write(f"{BASE}/entity/User.java", """
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;
}
""")

write(f"{BASE}/entity/Farm.java", """
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Farm {

    @Id
    @GeneratedValue
    private Long id;

    private String farmName;
    private String location;
    private String season;
    private Long ownerId;
}
""")

write(f"{BASE}/entity/Crop.java", """
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Crop {

    @Id
    @GeneratedValue
    private Long id;

    private String season;
    private double suitablePHMin;
    private double suitablePHMax;
}
""")

write(f"{BASE}/entity/Fertilizer.java", """
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fertilizer {

    @Id
    @GeneratedValue
    private Long id;

    private String npkRatio;
}
""")

# =====================================================
# 4. FIX SERVICE INTERFACES
# =====================================================
write(f"{BASE}/service/UserService.java", """
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    User create(User user);
    User getById(Long id);
    List<User> getAll();
    User update(Long id, User user);
    void delete(Long id);

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

# =====================================================
# 5. FIX SERVICE IMPLEMENTATIONS
# =====================================================
write(f"{BASE}/service/impl/UserServiceImpl.java", """
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) { return repo.save(user); }
    public User getById(Long id) { return repo.findById(id).orElse(null); }
    public List<User> getAll() { return repo.findAll(); }
    public User update(Long id, User user) { return repo.save(user); }
    public void delete(Long id) { repo.deleteById(id); }

    public User register(User user) { return repo.save(user); }
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
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

    private final FarmRepository repo;

    public FarmServiceImpl(FarmRepository repo) {
        this.repo = repo;
    }

    public Farm createFarm(Farm farm, long ownerId) {
        farm.setOwnerId(ownerId);
        return repo.save(farm);
    }

    public List<Farm> getFarmsByOwner(long ownerId) {
        return repo.findByOwnerId(ownerId);
    }
}
""")

write(f"{BASE}/service/impl/SuggestionServiceImpl.java", """
package com.example.demo.service.impl;

import com.example.demo.service.SuggestionService;

public class SuggestionServiceImpl implements SuggestionService {

    public String generateSuggestion(long farmId) {
        return "Generated suggestion for farm " + farmId;
    }

    public String getSuggestion(long farmId) {
        return "Suggestion for farm " + farmId;
    }
}
""")

print("\\n✅ FINAL CLEANUP & FIX COMPLETE")
