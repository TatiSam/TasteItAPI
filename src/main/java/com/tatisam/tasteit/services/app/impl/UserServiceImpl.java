package com.tatisam.tasteit.services.app.impl;

import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.entities.app.Dish;
import com.tatisam.tasteit.entities.auth.User;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.exceptions.app.UserResourcesException;
import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.payload.auth.LoginDTO;
import com.tatisam.tasteit.repositories.app.CountryRepository;
import com.tatisam.tasteit.repositories.app.DishRepository;
import com.tatisam.tasteit.repositories.auth.UserRepository;
import com.tatisam.tasteit.services.app.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service is used to work with resources of {@link User} from database
 * @implSpec {@link UserService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/04/22
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final DishRepository dishRepository;
    private final TypeMap<Country, CountryDTO> countryToDto;
    private final TypeMap<Dish, DishDTO> dishToDto;

    public UserServiceImpl(UserRepository userRepository,
                           CountryRepository countryRepository,
                           DishRepository dishRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.dishRepository = dishRepository;
        countryToDto = modelMapper.getTypeMap(Country.class, CountryDTO.class);
        dishToDto = modelMapper.getTypeMap(Dish.class, DishDTO.class);
    }

    /**
     * Add {@link Country} to user
     * @param dto {@link LoginDTO}
     * @param countryId {@link Country} id
     * @return String with message "Added"
     * @throws UserResourcesException if {@link User} already has this country
     * @since 22/04/22
     */
    @Override
    public String addCountryToUser(LoginDTO dto, long countryId) {
        var user = findUser(dto);
        var country = findCountry(countryId);
        var result = user.getCountries().add(country);
        if(!result)
            throw new UserResourcesException("User already has this country", "countryId", countryId);
        userRepository.save(user);
        return "Added";
    }

    /**
     * Delete  {@link Country} from user
     * @param dto {@link LoginDTO}
     * @param countryId {@link Country} id
     * @return String with message "Deleted"
     * @throws UserResourcesException if {@link User} does not have this country
     * @since 22/04/22
     */
    @Override
    public String deleteCountryFromUser(LoginDTO dto, long countryId) {
        var user = findUser(dto);
        var country = findCountry(countryId);
        var result = user.getCountries().remove(country);
        if(!result)
            throw new UserResourcesException("User does not have this country", "countryId", countryId);
        userRepository.save(user);
        return "Deleted";
    }

    /**
     * Add {@link Dish} to user
     * @param dto {@link LoginDTO}
     * @param dishId {@link Dish} id
     * @return String with message "Added"
     * @throws UserResourcesException if {@link User} already has this dish
     * @since 22/04/22
     */
    @Override
    public String addDishToUser(LoginDTO dto, long dishId) {
        var user = findUser(dto);
        var dish = findDish(dishId);
        var result = user.getDishes().add(dish);
        if(!result)
            throw new UserResourcesException("User already has this dish", "dishId", dishId);
        userRepository.save(user);
        return "Added";
    }

    /**
     * Delete {@link Dish} from user
     * @param dto {@link LoginDTO}
     * @param dishId {@link Dish} id
     * @return String with message "Deleted"
     * @throws UserResourcesException if {@link User} does not have this dish
     * @since 22/04/22
     */
    @Override
    public String deleteDishFromUser(LoginDTO dto, long dishId) {
        var user = findUser(dto);
        var dish = findDish(dishId);
        var result = user.getDishes().remove(dish);
        if(!result)
            throw new UserResourcesException("User does not have this dish", "dishId", dishId);
        userRepository.save(user);
        return "Deleted";
    }

    /**
     * Get List of {@link Country} from user
     * @param dto {@link LoginDTO}
     * @return List of {@link CountryDTO}
     * @since 22/04/22
     */
    @Override
    public List<CountryDTO> getUsersCountries(LoginDTO dto) {
        var user = findUser(dto);
        var countries = user.getCountries();
        var content = countries.stream().map(countryToDto::map).collect(Collectors.toList());
        return content;
    }

    /**
     * Get List of {@link Dish} from user
     * @param dto {@link LoginDTO}
     * @return List of {@link DishDTO}
     * @since 22/04/22
     */
    @Override
    public List<DishDTO> getUsersDishes(LoginDTO dto) {
        var user = findUser(dto);
        var dishes = user.getDishes();
        var content = dishes.stream().map(dishToDto::map).collect(Collectors.toList());
        return content;
    }

    /**
     * Helper method find {@link User} from database
     * @param dto {@link LoginDTO}
     * @return {@link User}
     * @throws UsernameNotFoundException if {@link User} with userName or email not exists in database
     * @since 22/04/22
     */
    private User findUser(LoginDTO dto){
        var user = userRepository
                .findUserByUserNameOrEmail(dto.getUserNameOrEmail(), dto.getUserNameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User name or Email not found"));
        return user;
    }

    /**
     * Helper method find {@link Country} from database
     * @param id {@link Country} id
     * @return {@link Country}
     * @throws ResourceNotFoundException if {@link Country} with id not exists in database
     * @since 22/04/22
     */
    private Country findCountry(long id){
        Country country = countryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Country", "id", id));
        return country;
    }

    /**
     * Helper method find {@link Dish} from database
     * @param id {@link Dish} id
     * @return {@link Dish}
     * @throws ResourceNotFoundException if {@link Dish} with id not exists in database
     * @since 22/04/22
     */
    private Dish findDish(long id){
        Dish dish = dishRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dish", "id", id));
        return dish;
    }
}
