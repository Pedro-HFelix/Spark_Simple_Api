package spark.api.controllers.food;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.api.controllers.ApiResponse;
import spark.api.exceptions.ResourceNotFoundException;
import spark.api.repository.IFoodRepository;
import spark.api.useCases.foods.DeleteFoodByIdUseCase;

public class DeleteFoodHandler extends FoodBaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFoodHandler.class);

    public DeleteFoodHandler(IFoodRepository foodRepository, Gson gson) {
        super(foodRepository, gson);
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            String id = request.params(":id");

            new DeleteFoodByIdUseCase(foodRepository).execute(id);
            response.status(200);

            return gson.toJson(new ApiResponse(true, "Food deleted successfully"));
        } catch (IllegalArgumentException e) {
            response.status(400);
            return gson.toJson(new ApiResponse(false, "Invalid UUID format: " + request.params(":id")));
        } catch (ResourceNotFoundException e) {
            response.status(404);
            return gson.toJson(new ApiResponse(false, e.getMessage()));
        }
    }
}
