package spark.api.useCases.foods;

import spark.api.dtos.FoodCreateDTO;
import spark.api.dtos.FoodResponseDTO;
import spark.api.exceptions.ValidationException;
import spark.api.repository.IFoodRepository;
import spark.api.utils.validators.FoodValidator;

import java.util.List;

public class CreateFoodUseCase {
	private final IFoodRepository foodRepository;

	public CreateFoodUseCase(IFoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	public FoodResponseDTO execute(FoodCreateDTO foodCreateDTO) {
		List<String> errors = FoodValidator.validateCreateDTO(foodCreateDTO);
		if(!errors.isEmpty()) throw new ValidationException(errors);

		return foodRepository.createFood(foodCreateDTO);
	}
}
