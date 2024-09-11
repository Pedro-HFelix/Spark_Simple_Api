package spark.api.dtos;

import java.util.UUID;

public class FoodCreateDTO {
    private UUID foodId;
    private String name;
    private String description;
    private float price;
    private String category;
    private boolean isAvailable;
    private int calories;
    private String ingredients;
    private int preparationTime;
    private float rating;

    public FoodCreateDTO(String name, String description, float price, String category, boolean isAvailable,
                         int calories, String ingredients, int preparationTime, float rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.calories = calories;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.rating = rating;
    }

    public FoodCreateDTO(UUID foodId, String name, String description, float price, String category, boolean isAvailable,
                         int calories, String ingredients, int preparationTime, float rating) {
        this.foodId = foodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.calories = calories;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.rating = rating;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public void setFoodId(UUID foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
