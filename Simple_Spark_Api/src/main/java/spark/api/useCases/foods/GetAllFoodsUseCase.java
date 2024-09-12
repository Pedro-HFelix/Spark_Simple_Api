package spark.api.useCases.foods;

import spark.api.dtos.FoodResponseDTO;
import spark.api.repository.IFoodRepository;

import java.util.List;

public class GetAllFoodsUseCase {

	private final IFoodRepository foodRepository;

	public GetAllFoodsUseCase(IFoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	public List<FoodResponseDTO> execute() {
		return foodRepository.getAllFoods();
	}
}

