package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.repository.IFoodRepository;

import java.util.UUID;

public class DeleteFoodHandler extends FoodBaseHandler{
    public DeleteFoodHandler(IFoodRepository foodRepository, Gson gson) {
        super(foodRepository, gson);
    }

    @Override
    public Object handle(Request request, Response response) {
        String id = request.params(":id");
        boolean deleted = foodRepository.deleteFood(UUID.fromString(id));
        response.status(deleted ? 200 : 400);
        return gson.toJson(new ApiResponse(deleted, deleted ? "Food deleted successfully" : "Failed to delete food"));
    }

}
