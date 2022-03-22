package com.tatisam.tasteit.services.app;

import com.tatisam.tasteit.payload.app.DishDTO;

import java.util.List;

/**
 * Interface for implementing by Dish Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
public interface DishService {
    DishDTO createDish(long countryId, DishDTO dto);
    List<DishDTO> getDishesByCountryId(long countryId);
    DishDTO updateDishById(long id, DishDTO dto);
    DishDTO deleteDishById(long id);
}
