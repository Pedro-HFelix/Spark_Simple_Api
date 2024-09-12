package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodResponseDTO;
import spark.api.repository.IFoodRepository;
import spark.api.useCases.foods.GetAllFoodsUseCase;

import java.util.List;

public class GetAllFoodsHandler extends FoodBaseHandler {
	public GetAllFoodsHandler(IFoodRepository foodRepository, Gson gson) {
		super(foodRepository, gson);
	}

	@Override
	public Object handle(Request request, Response response) {
		List<FoodResponseDTO> foods = new GetAllFoodsUseCase(foodRepository).execute();
		return gson.toJson(new ApiResponse(true, "Foods retrieved successfully", foods));
	}
}
