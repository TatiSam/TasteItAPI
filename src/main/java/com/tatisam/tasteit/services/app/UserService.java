package com.tatisam.tasteit.services.app;

import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.payload.auth.LoginDTO;

import java.util.List;

/**
 * Interface for implementing by User Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/04/22
 */
public interface UserService {
    String addCountryToUser(LoginDTO dto, long countryId);
    String deleteCountryFromUser(LoginDTO dto, long countryId);
    String addDishToUser(LoginDTO dto, long dishId);
    String deleteDishFromUser(LoginDTO dto, long dishId);
    List<CountryDTO> getUsersCountries(LoginDTO dto);
    List<DishDTO> getUsersDishes(LoginDTO dto);
}
