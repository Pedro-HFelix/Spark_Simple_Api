package spark.api.repository.postgres;

import spark.api.dtos.FoodCreateDTO;
import spark.api.dtos.FoodResponseDTO;
import spark.api.dtos.FoodUpdateDTO;
import spark.api.exceptions.CreationException;
import spark.api.exceptions.DeleteException;
import spark.api.exceptions.ResourceNotFoundException;
import spark.api.exceptions.UpdateException;
import spark.api.infra.database.DatabaseConfig;
import spark.api.repository.IFoodRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodRepository implements IFoodRepository {

    @Override
    public FoodResponseDTO createFood(FoodCreateDTO food) {
        String sql = "INSERT INTO foods (name, description, price, category, is_available, calories, ingredients, preparation_time, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id, name, description, price, category, is_available, calories, ingredients, preparation_time, rating, created_at, updated_at";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setFloat(3, food.getPrice());
            statement.setString(4, food.getCategory());
            statement.setBoolean(5, food.isAvailable());
            statement.setInt(6, food.getCalories());
            statement.setString(7, food.getIngredients());
            statement.setInt(8, food.getPreparationTime());
            statement.setFloat(9, food.getRating());

            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    throw new CreationException("Failed to retrieve the created food.");
                }

                return builderFoodResponseDTO(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreationException("An error occurred while creating the food.");
        }
    }

    @Override
    public FoodResponseDTO updateFood(FoodUpdateDTO food) {
        String updateSql = "UPDATE foods SET name = ?, description = ?, price = ?, category = ?, is_available = ?, calories = ?, ingredients = ?, preparation_time = ?, rating = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ? RETURNING *";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {

            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setFloat(3, food.getPrice());
            statement.setString(4, food.getCategory());
            statement.setBoolean(5, food.isAvailable());
            statement.setInt(6, food.getCalories());
            statement.setString(7, food.getIngredients());
            statement.setInt(8, food.getPreparationTime());
            statement.setFloat(9, food.getRating());
            statement.setObject(10, food.getFoodId(), Types.OTHER);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return builderFoodResponseDTO(rs);
                } else {
                    throw new UpdateException("Failed to update food.");
                }
            }
        } catch (SQLException e) {
            throw new UpdateException("Error updating food", e);
        }
    }


    @Override
    public void deleteFood(UUID id) {
        String sql = "DELETE FROM foods WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(sql)) {

            deleteStatement.setObject(1, id, Types.OTHER);
            int affectedRows = deleteStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DeleteException("Failed to delete food item with ID: " + id);
            }

        } catch (SQLException e) {
            throw new DeleteException("Error deleting food item", e);
        }
    }


    @Override
    public List<FoodResponseDTO> getAllFoods() {
        List<FoodResponseDTO> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                FoodResponseDTO food = builderFoodResponseDTO(rs);

                foods.add(food);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public FoodResponseDTO findById(UUID id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id, Types.OTHER);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return builderFoodResponseDTO(rs);
                } else {
                    throw new ResourceNotFoundException("Food item not found for ID: " + id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving food by ID", e);
        }
    }

    private FoodResponseDTO builderFoodResponseDTO(ResultSet rs) throws SQLException {
        return new FoodResponseDTO(
                UUID.fromString(rs.getString("id")),
                rs.getString("name"),
                rs.getString("description"),
                rs.getFloat("price"),
                rs.getString("category"),
                rs.getBoolean("is_available"),
                rs.getInt("calories"),
                rs.getString("ingredients"),
                rs.getInt("preparation_time"),
                rs.getFloat("rating"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
