package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodCreateDTO;
import spark.api.repository.IFoodRepository;

import java.util.UUID;

public class UpdateFoodHandler extends FoodBaseHandler {
	public UpdateFoodHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		String id = request.params(":id");
		FoodCreateDTO food = gson.fromJson(request.body(), FoodCreateDTO.class);
		food.setFoodId(UUID.fromString(id));
		boolean updated = foodRepository.updateFood(food);
		response.status(updated ? 200 : 400);
		return gson.toJson(new ApiResponse(updated, updated ? "Food updated successfully" : "Failed to update food"));
	}
}
