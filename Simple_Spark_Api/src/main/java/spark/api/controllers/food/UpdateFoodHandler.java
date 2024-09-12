package spark.api.controllers.food;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodUpdateDTO;
import spark.api.dtos.FoodResponseDTO;
import spark.api.exceptions.UpdateException;
import spark.api.repository.IFoodRepository;
import spark.api.useCases.foods.UpdateFoodUseCase;

import java.util.UUID;

public class UpdateFoodHandler extends FoodBaseHandler {
	private static final Logger logger = LoggerFactory.getLogger(UpdateFoodHandler.class);

	public UpdateFoodHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		String id = request.params(":id");
		FoodUpdateDTO food = gson.fromJson(request.body(), FoodUpdateDTO.class);

		try {
			food.setFoodId(UUID.fromString(id));

			FoodResponseDTO updatedFood = new UpdateFoodUseCase(foodRepository).execute(food);

			if (updatedFood != null) {
				response.status(200);
				return gson.toJson(new ApiResponse(true, "Food updated successfully", updatedFood));
			} else {
				response.status(400);
				return gson.toJson(new ApiResponse(false, "Failed to update food"));
			}
		} catch (IllegalArgumentException e) {
			response.status(400);
			return gson.toJson(new ApiResponse(false, "Invalid data: " + e.getMessage()));
		} catch (UpdateException e) {
			logger.error(e.getMessage());
			response.status(500);
			return gson.toJson(new ApiResponse(false, "Error updating food "));
		}
	}
}
