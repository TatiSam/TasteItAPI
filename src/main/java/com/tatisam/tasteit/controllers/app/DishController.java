package com.tatisam.tasteit.controllers.app;

import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.services.app.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for work with {@link DishService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@RestController
@RequestMapping("api/1")
@CrossOrigin(origins = {"https://tasteit-tatisam.herokuapp.com"})
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     * Create valid {@link com.tatisam.tasteit.entities.app.Dish}
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @param dto {@link DishDTO}
     * @return {@link ResponseEntity} with status Created and {@link DishDTO}
     * @since 03/03/22
     */
    @PostMapping("/countries/{id}/dishes")
    public ResponseEntity<DishDTO> createDish(@PathVariable("id") long countryId,
                                              @Valid @RequestBody DishDTO dto){
        return new ResponseEntity(dishService.createDish(countryId, dto), HttpStatus.CREATED);
    }

    /**
     * Get List of {@link com.tatisam.tasteit.entities.app.Dish} by {@link com.tatisam.tasteit.entities.app.Country} id
     * @param countryId {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and List of {@link DishDTO}
     * @since 03/03/22
     */
    @GetMapping("/countries/{id}/dishes")
    public ResponseEntity<List<DishDTO>> getDishesByCountryId(@PathVariable("id") long countryId){
        return ResponseEntity.ok()
                .body(dishService.getDishesByCountryId(countryId));
    }

    /**
     * Get {@link com.tatisam.tasteit.entities.app.Dish} by id
     * @param id {@link com.tatisam.tasteit.entities.app.Dish} id
     * @return {@link ResponseEntity} with status Ok and {@link DishDTO}
     * @since 22/04/22
     */
    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable long id){
        return ResponseEntity.ok().body(dishService.getDishById(id));
    }

    /**
     * Update {@link com.tatisam.tasteit.entities.app.Dish} in database by id
     * @param id {@link com.tatisam.tasteit.entities.app.Dish} id
     * @param dto {@link DishDTO}
     * @return {@link ResponseEntity} with status Ok and updated {@link DishDTO}
     * @since 03/03/22
     */
    @PutMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> updateDishById(@PathVariable long id,
                                                  @Valid @RequestBody DishDTO dto){
        return ResponseEntity.ok()
                .body(dishService.updateDishById(id, dto));
    }

    /**
     * Delete {@link com.tatisam.tasteit.entities.app.Dish} by id
     * @param id {@link com.tatisam.tasteit.entities.app.Dish} id
     * @return {@link ResponseEntity} with status Ok and {@link String} with message
     * @since 03/03/22
     */
    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> deleteDishById(@PathVariable long id){
        return ResponseEntity.ok()
                .body(dishService.deleteDishById(id));
    }
}
