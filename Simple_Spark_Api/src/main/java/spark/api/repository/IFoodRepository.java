package spark.api.repository;

import spark.api.dtos.FoodCreateDTO;
import spark.api.dtos.FoodResponseDTO;
import spark.api.dtos.FoodUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IFoodRepository {
    /**
     * Cria um novo alimento.
     * @param food Objeto com os dados do alimento a ser criado.
     * @return O identificador do alimento criado.
     */
    FoodResponseDTO createFood(FoodCreateDTO food);

    /**
     * Atualiza um alimento existente.
     * @param food Objeto com os dados atualizados do alimento.
     * @return true se a atualização foi bem-sucedida, false caso contrário.
     */
    FoodResponseDTO updateFood(FoodUpdateDTO food);

    /**
     * Remove um alimento pelo identificador.
     * @param id Identificador do alimento a ser excluído.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    void deleteFood(UUID id);

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
    FoodResponseDTO findById(UUID id);
}
