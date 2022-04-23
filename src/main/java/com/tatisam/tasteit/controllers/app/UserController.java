package com.tatisam.tasteit.controllers.app;

import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.payload.auth.LoginDTO;
import com.tatisam.tasteit.services.app.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for work with {@link com.tatisam.tasteit.services.app.UserService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/04/22
 */
@RestController
@RequestMapping("api/1/user")
@CrossOrigin(origins = {"http://localhost:8080"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Add {@link com.tatisam.tasteit.entities.app.Country} to user
     * @param dto {@link LoginDTO}
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and message "Added"
     * @since 22/04/22
     */
    @PostMapping("/countries/{id}")
    public ResponseEntity<String> addCountryToUser(
            @Valid @RequestBody LoginDTO dto,
            @PathVariable("id") long countryId){
        return ResponseEntity.ok().body(userService.addCountryToUser(dto, countryId));
    }

    /**
     * Delete  {@link com.tatisam.tasteit.entities.app.Country} from user
     * @param dto {@link LoginDTO}
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and message "Deleted"
     * @since 22/04/22
     */
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<String> deleteCountryFromUser(
            @Valid @RequestBody LoginDTO dto,
            @PathVariable("id") long countryId){
        return ResponseEntity.ok().body(userService.deleteCountryFromUser(dto, countryId));
    }

    /**
     * Add {@link com.tatisam.tasteit.entities.app.Dish} to user
     * @param dto {@link LoginDTO}
     * @param dishId {@link com.tatisam.tasteit.entities.app.Dish} id
     * @return {@link ResponseEntity} with status Ok and message "Added"
     * @since 22/04/22
     */
    @PostMapping("/dishes/{id}")
    public ResponseEntity<String> addDishToUser(
            @Valid @RequestBody LoginDTO dto,
            @PathVariable("id") long dishId){
        return ResponseEntity.ok().body(userService.addDishToUser(dto, dishId));
    }

    /**
     * Delete {@link com.tatisam.tasteit.entities.app.Dish} from user
     * @param dto {@link LoginDTO}
     * @param dishId {@link com.tatisam.tasteit.entities.app.Dish} id
     * @return {@link ResponseEntity} with status Ok and message "Deleted"
     * @since 22/04/22
     */
    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<String> deleteDishFromUser(
            @Valid @RequestBody LoginDTO dto,
            @PathVariable("id") long dishId){
        return ResponseEntity.ok().body(userService.deleteDishFromUser(dto, dishId));
    }

    /**
     * Get List of {@link com.tatisam.tasteit.entities.app.Country} from user
     * @param dto {@link LoginDTO}
     * @return {@link ResponseEntity} with status Ok and List of {@link CountryDTO}
     * @since 22/04/22
     */
    @GetMapping("/countries")
    public ResponseEntity<List<CountryDTO>> getUsersCountries(@Valid @RequestBody LoginDTO dto){
        return ResponseEntity.ok().body(userService.getUsersCountries(dto));
    }

    /**
     * Get List of {@link com.tatisam.tasteit.entities.app.Dish} from user
     * @param dto {@link LoginDTO}
     * @return {@link ResponseEntity} with status Ok and List of {@link DishDTO}
     * @since 22/04/22
     */
    @GetMapping("/dishes")
    public ResponseEntity<List<DishDTO>> getUsersDishes(@Valid @RequestBody LoginDTO dto){
        return ResponseEntity.ok().body(userService.getUsersDishes(dto));
    }
}
