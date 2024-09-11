package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodCreateDTO;
import spark.api.repository.IFoodRepository;

public class CreateFoodHandler extends FoodBaseHandler {

	public CreateFoodHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		FoodCreateDTO food = gson.fromJson(request.body(), FoodCreateDTO.class);
		boolean created = foodRepository.createFood(food);
		response.status(created ? 201 : 400);
		return gson.toJson(new ApiResponse(created, created ? "Food created successfully" : "Failed to create food"));
	}
}
