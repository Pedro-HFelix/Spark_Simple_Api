package spark.api.controllers.food;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodCreateDTO;
import spark.api.exceptions.CreationException;
import spark.api.exceptions.ValidationException;
import spark.api.repository.IFoodRepository;
import spark.api.useCases.foods.CreateFoodUseCase;

public class CreateFoodHandler extends FoodBaseHandler {
	private static final Logger logger = LoggerFactory.getLogger(CreateFoodHandler.class);

	public CreateFoodHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		FoodCreateDTO food = gson.fromJson(request.body(), FoodCreateDTO.class);

		try {
			var createdFood = new CreateFoodUseCase(foodRepository).execute(food);

			response.status(201);
			return gson.toJson(new ApiResponse(true, "Food created successfully", createdFood));

		} catch (ValidationException e) {
			response.status(400);
			return gson.toJson(new ApiResponse(false, "Invalid request: " + e.getErrors()));

		} catch (CreationException e) {
			logger.error("Food creation failed: {}", e.getMessage(), e);
			response.status(500);
			return gson.toJson(new ApiResponse(false, "Failed to create food"));
		}
	}
}
