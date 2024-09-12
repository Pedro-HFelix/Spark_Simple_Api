package spark.api.controllers.food;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.api.repository.IFoodRepository;

public abstract class FoodBaseHandler implements Route {
    protected final IFoodRepository foodRepository;
    protected final Gson gson;

    public FoodBaseHandler(IFoodRepository foodRepository, Gson gson) {
        this.foodRepository = foodRepository;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        throw new Exception("Invalid handler");
    }
}
