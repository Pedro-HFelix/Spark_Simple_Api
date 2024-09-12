package spark.api.useCases.foods;

import spark.api.dtos.FoodResponseDTO;
import spark.api.repository.IFoodRepository;

import java.util.UUID;

public class FindFoodByIdUseCase {
    private final IFoodRepository foodRepository;

    public FindFoodByIdUseCase(IFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodResponseDTO execute(String id) {
        UUID foodId;
        try {
            foodId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id, e);
        }

        return foodRepository.findById(foodId);
    }
}
