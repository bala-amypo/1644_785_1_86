
public interface CatalogService {
    Crop addCrop(Crop crop);
    Fertilizer addFertilizer(Fertilizer fertilizer);
    java.util.List<Crop> findSuitableCrops(Double ph, Double water, String season);
    java.util.List<Fertilizer> findFertilizersForCrops(java.util.List<String> cropNames);
}

