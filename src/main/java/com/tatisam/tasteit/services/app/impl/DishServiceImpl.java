package com.tatisam.tasteit.services.app.impl;

import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.entities.app.Dish;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.repositories.app.CountryRepository;
import com.tatisam.tasteit.repositories.app.DishRepository;
import com.tatisam.tasteit.services.app.DishService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Dish Service is used to work with {@link Dish} from database
 * @implSpec {@link DishService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@Service
public class DishServiceImpl implements DishService {
    private final CountryRepository countryRepository;
    private final DishRepository dishRepository;
    private final TypeMap<Dish, DishDTO> toDto;
    private final TypeMap<DishDTO, Dish> toDish;

    public DishServiceImpl(CountryRepository countryRepository, DishRepository dishRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.dishRepository = dishRepository;
        toDto = modelMapper.createTypeMap(Dish.class, DishDTO.class);
        toDish = modelMapper.createTypeMap(DishDTO.class, Dish.class);
    }

    /**
     * Create {@link Dish} in database
     * @param countryId {@link Country} id
     * @param dto {@link DishDTO}
     * @return {@link DishDTO} from created {@link Dish}
     * @since 03/03/22
     */
    @Override
    public DishDTO createDish(long countryId, DishDTO dto) {
        var country = countryRepository.findById(countryId)
                .orElseThrow(()-> new ResourceNotFoundException("Country", "id", countryId));
        var dish = toDish.map(dto);
        dish.setCountry(country);
        var savedDish = dishRepository.save(dish);
        return toDto.map(savedDish);
    }

    /**
     * Get List of {@link DishDTO} by {@link Country} id
     * @param countryId {@link Country} id
     * @return List of {@link DishDTO}
     * @since 03/03/22
     */
    @Override
    public List<DishDTO> getDishesByCountryId(long countryId) {
        var dishes = dishRepository.findDishesByCountryId(countryId);
        return dishes.stream().map(toDto::map).collect(Collectors.toList());
    }

    /**
     * Update {@link Dish} in database by id
     * @param id {@link Dish} id
     * @param dto {@link DishDTO}
     * @return {@link DishDTO}
     * @since 03/03/22
     */
    @Override
    public DishDTO updateDishById(long id, DishDTO dto) {
        var beforeEdit = dishRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dish", "id", id));
        var edited = toDish.map(dto);
        edited.setId(beforeEdit.getId());
        edited.setCountry(beforeEdit.getCountry());
        var saved = dishRepository.save(edited);
        return toDto.map(saved);
    }

    /**
     * Delete {@link Dish} from database by id
     * @param id {@link Dish} id
     * @since 03/03/22
     */
    @Override
    public DishDTO deleteDishById(long id) {
        var dish = dishRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dish", "id", id));
        dishRepository.delete(dish);
        return toDto.map(dish);
    }
}
