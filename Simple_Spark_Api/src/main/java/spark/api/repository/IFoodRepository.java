package spark.api.repository;

import spark.api.dtos.FoodCreateDTO;
import spark.api.dtos.FoodResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFoodRepository {
    /**
     * Cria um novo alimento.
     * @param food Objeto com os dados do alimento a ser criado.
     * @return O identificador do alimento criado.
     */
    boolean createFood(FoodCreateDTO food);

    /**
     * Atualiza um alimento existente.
     * @param food Objeto com os dados atualizados do alimento.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    boolean updateFood(FoodCreateDTO food);

    /**
     * Remove um alimento pelo identificador.
     * @param id Identificador do alimento a ser excluído.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    boolean deleteFood(UUID id);

    /**
     * Retorna todos os alimentos.
     * @return Lista de alimentos.
     */
    List<FoodResponseDTO> getAllFoods();

    /**
     * Retorna um alimento pelo identificador.
     * @param id Identificador do alimento.
     * @return Um Optional contendo o alimento se encontrado, ou vazio se não encontrado.
     */
    Optional<FoodResponseDTO> getFoodById(UUID id);
}
