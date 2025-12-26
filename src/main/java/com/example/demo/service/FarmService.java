
public interface FarmService {
    Farm createFarm(Farm farm, Long ownerId);
    java.util.List<Farm> getFarmsByOwner(Long ownerId);
    Farm getFarmById(Long farmId);
}