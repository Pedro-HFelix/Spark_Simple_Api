package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodResponseDTO;
import spark.api.repository.IFoodRepository;

import java.util.Optional;
import java.util.UUID;

public class GetFoodByIdHandler extends FoodBaseHandler{
	public GetFoodByIdHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		String id = request.params(":id");
		Optional<FoodResponseDTO> food = foodRepository.getFoodById(UUID.fromString(id));
		if (food.isPresent()) {
			return gson.toJson(new ApiResponse(true, "Food retrieved successfully", food.get()));
		} else {
			response.status(404);
			return gson.toJson(new ApiResponse(false, "Food not found"));
		}
	}

}
