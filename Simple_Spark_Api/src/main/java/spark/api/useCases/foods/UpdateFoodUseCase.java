package spark.api.useCases.foods;

import spark.api.dtos.FoodResponseDTO;
import spark.api.dtos.FoodUpdateDTO;
import spark.api.repository.IFoodRepository;

import java.util.UUID;

public class UpdateFoodUseCase {

	private final IFoodRepository foodRepository;

	public UpdateFoodUseCase(IFoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	public FoodResponseDTO execute(FoodUpdateDTO foodUpdateDTO) {
		UUID foodId = foodUpdateDTO.getFoodId();

		foodRepository.findById(foodId);

		return foodRepository.updateFood(foodUpdateDTO);
	}
}
