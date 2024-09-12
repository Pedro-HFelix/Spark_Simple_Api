package spark.api.useCases.foods;

import spark.api.repository.IFoodRepository;

import java.util.UUID;


public class DeleteFoodByIdUseCase {

	private final IFoodRepository foodRepository;

	public DeleteFoodByIdUseCase(IFoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	public void execute(String id) {
		UUID foodId;
		try {
			foodId = UUID.fromString(id);

			foodRepository.deleteFood(foodId);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid UUID format: " + id, e);
		}
	}
}
