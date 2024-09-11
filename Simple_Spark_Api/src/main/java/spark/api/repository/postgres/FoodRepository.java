package spark.api.repository.postgres;

import spark.api.dtos.FoodCreateDTO;
import spark.api.dtos.FoodResponseDTO;
import spark.api.infra.database.DatabaseConfig;
import spark.api.repository.IFoodRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FoodRepository implements IFoodRepository {

    @Override
    public boolean createFood(FoodCreateDTO food) {
        String sql = "INSERT INTO foods (name, description, price, category, is_available, calories, ingredients, preparation_time, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

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

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateFood(FoodCreateDTO food) {
        String sql = "UPDATE foods SET name = ?, description = ?, price = ?, category = ?, is_available = ?, calories = ?, ingredients = ?, preparation_time = ?, rating = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
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
            statement.setObject(10, food.getFoodId(), Types.OTHER);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFood(UUID id) {
        String sql = "DELETE FROM foods WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id, Types.OTHER);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
    public Optional<FoodResponseDTO> getFoodById(UUID id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id, Types.OTHER);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                FoodResponseDTO food = builderFoodResponseDTO(rs);
                return Optional.of(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
