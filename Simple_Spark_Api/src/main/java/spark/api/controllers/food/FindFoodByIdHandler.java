package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodResponseDTO;
import spark.api.exceptions.ResourceNotFoundException;
import spark.api.repository.IFoodRepository;
import spark.api.useCases.foods.FindFoodByIdUseCase;

public class FindFoodByIdHandler extends FoodBaseHandler {

	public FindFoodByIdHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		String id = request.params(":id");

		try {
			FoodResponseDTO food = new FindFoodByIdUseCase(foodRepository).execute(id);
			return gson.toJson(new ApiResponse(true, "Food retrieved successfully", food));
		}catch (IllegalArgumentException e) {
			response.status(400);
			return gson.toJson(new ApiResponse(false, "Invalid UUID format: " + id));
		}
		catch (ResourceNotFoundException e) {
			response.status(404);
			return gson.toJson(new ApiResponse(false, e.getMessage()));
		}
	}
}
