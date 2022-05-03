package com.tatisam.tasteit.services.app;

import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.payload.app.UserDTO;

import java.util.List;

/**
 * Interface for implementing by User Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/04/22
 */
public interface UserService {
    CountryDTO addCountryToUser(UserDTO dto, long countryId);
    CountryDTO deleteCountryFromUser(UserDTO dto, long countryId);
    DishDTO addDishToUser(UserDTO dto, long dishId);
    DishDTO deleteDishFromUser(UserDTO dto, long dishId);
    List<CountryDTO> getUserCountries(String userNameOrEmail);
    List<DishDTO> getUserDishes(String userNameOrEmail);
}
