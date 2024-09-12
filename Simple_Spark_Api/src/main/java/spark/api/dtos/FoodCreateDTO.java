package spark.api.dtos;

public class FoodCreateDTO {
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

    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (calories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative.");
        }
        if (ingredients == null || ingredients.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredients are required.");
        }
        if (preparationTime < 0) {
            throw new IllegalArgumentException("Preparation time cannot be negative.");
        }
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
    }
}
