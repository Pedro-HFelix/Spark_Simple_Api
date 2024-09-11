package spark.api.controllers.food;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.api.infra.configGson.LocalDateTimeAdapter;
import spark.api.repository.IFoodRepository;
import spark.api.repository.postgres.FoodRepository;

import java.time.LocalDateTime;

import static spark.Spark.*;

public class FoodRoutes {

	private final IFoodRepository foodRepository;
	private final Gson gson;

	public FoodRoutes() {
		this.foodRepository = new FoodRepository();
		this.gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.create();

		configureRoutes();
	}

	private void configureRoutes() {
		post("/foods", new CreateFoodHandler(foodRepository, gson));
		get("/foods", new GetAllFoodsHandler(foodRepository, gson));
		get("/foods/:id", new GetFoodByIdHandler(foodRepository, gson));
		put("/foods/:id", new UpdateFoodHandler(foodRepository, gson));
		delete("/foods/:id", new DeleteFoodHandler(foodRepository, gson));
	}
}
