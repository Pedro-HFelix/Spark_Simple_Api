package spark.api.utils.validators;

import spark.api.dtos.FoodCreateDTO;

import java.util.ArrayList;
import java.util.List;

public class FoodValidator {
	public static List<String> validateCreateDTO(FoodCreateDTO food) {
		List<String> errors = new ArrayList<>();

		if (food.getName() == null || food.getName().trim().isEmpty()) {
			errors.add("Name is required.");
		}
		if (food.getDescription() == null || food.getDescription().trim().isEmpty()) {
			errors.add("Description is required.");
		}
		if (food.getPrice() <= 0) {
			errors.add("Price must be greater than zero.");
		}
		if (food.getCategory() == null || food.getCategory().trim().isEmpty()) {
			errors.add("Category is required.");
		}
		if (food.getCalories() < 0) {
			errors.add("Calories cannot be negative.");
		}
		if (food.getIngredients() == null || food.getIngredients().trim().isEmpty()) {
			errors.add("Ingredients are required.");
		}
		if (food.getPreparationTime() < 0) {
			errors.add("Preparation time cannot be negative.");
		}
		if (food.getRating() < 0 || food.getRating() > 5) {
			errors.add("Rating must be between 0 and 5.");
		}

		return errors;
	}

}
