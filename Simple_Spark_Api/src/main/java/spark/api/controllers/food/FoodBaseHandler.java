package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.api.controllers.ApiResponse;
import spark.api.dtos.FoodCreateDTO;
import spark.api.repository.IFoodRepository;

public class FoodBaseHandler implements Route {
    protected final IFoodRepository foodRepository;
    protected final Gson gson;

    public FoodBaseHandler(IFoodRepository foodRepository, Gson gson) {
        this.foodRepository = foodRepository;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) {
        FoodCreateDTO food = gson.fromJson(request.body(), FoodCreateDTO.class);
        boolean created = foodRepository.createFood(food);
        response.status(created ? 201 : 400);
        return gson.toJson(new ApiResponse(created));
    }
}
