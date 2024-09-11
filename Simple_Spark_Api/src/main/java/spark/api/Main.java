package spark.api;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import spark.Spark;
import spark.api.controllers.food.FoodRoutes;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Dotenv dotenv = Dotenv.load();
        int port = Integer.parseInt(dotenv.get("SERVER_PORT", "4567"));
        port(port);

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });


        before((request, response) -> response.type("application/json"));

        get("/health", (request, response) -> {
            return gson.toJson(new HealthResponse("Server is healthy"));
        });

        new FoodRoutes();

        exception(Exception.class, (e, request, response) -> {
            response.status(500);
            response.body("Internal Server Error: " + e.getMessage());
        });

    }
    static class HealthResponse {
        private final String message;

        public HealthResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

