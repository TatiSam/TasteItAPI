package com.tatisam.tasteit.services.app.impl;

import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.entities.app.Dish;
import com.tatisam.tasteit.entities.auth.User;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.exceptions.app.UserResourcesException;
import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.DishDTO;
import com.tatisam.tasteit.payload.app.UserDTO;
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
     * @param dto {@link UserDTO}
     * @param countryId {@link Country} id
     * @return added {@link CountryDTO}
     * @throws UserResourcesException if {@link User} already has this country
     * @since 22/04/22
     */
    @Override
    public CountryDTO addCountryToUser(UserDTO dto, long countryId) {
        var user = findUser(dto);
        var country = findCountry(countryId);
        var result = user.getCountries().add(country);
        if(!result)
            throw new UserResourcesException("User already has this country", "countryId", countryId);
        userRepository.save(user);
        return countryToDto.map(country);
    }

    /**
     * Delete  {@link Country} from user
     * @param dto {@link UserDTO}
     * @param countryId {@link Country} id
     * @return deleted {@link CountryDTO}
     * @throws UserResourcesException if {@link User} does not have this country
     * @since 22/04/22
     */
    @Override
    public CountryDTO deleteCountryFromUser(UserDTO dto, long countryId) {
        var user = findUser(dto);
        var country = findCountry(countryId);
        var result = user.getCountries().remove(country);
        if(!result)
            throw new UserResourcesException("User does not have this country", "countryId", countryId);
        userRepository.save(user);
        return countryToDto.map(country);
    }

    /**
     * Add {@link Dish} to user
     * @param dto {@link UserDTO}
     * @param dishId {@link Dish} id
     * @return added {@link DishDTO}
     * @throws UserResourcesException if {@link User} already has this dish
     * @since 22/04/22
     */
    @Override
    public DishDTO addDishToUser(UserDTO dto, long dishId) {
        var user = findUser(dto);
        var dish = findDish(dishId);
        var result = user.getDishes().add(dish);
        if(!result)
            throw new UserResourcesException("User already has this dish", "dishId", dishId);
        userRepository.save(user);
        return dishToDto.map(dish);
    }

    /**
     * Delete {@link Dish} from user
     * @param dto {@link UserDTO}
     * @param dishId {@link Dish} id
     * @return deleted {@link DishDTO}
     * @throws UserResourcesException if {@link User} does not have this dish
     * @since 22/04/22
     */
    @Override
    public DishDTO deleteDishFromUser(UserDTO dto, long dishId) {
        var user = findUser(dto);
        var dish = findDish(dishId);
        var result = user.getDishes().remove(dish);
        if(!result)
            throw new UserResourcesException("User does not have this dish", "dishId", dishId);
        userRepository.save(user);
        return dishToDto.map(dish);
    }

    /**
     * Get List of {@link Country} from user
     * @param userNameOrEmail userName or email
     * @return List of {@link CountryDTO}
     * @since 22/04/22
     */
    @Override
    public List<CountryDTO> getUserCountries(String userNameOrEmail) {
        var user = findUser(userNameOrEmail);
        var countries = user.getCountries();
        return countries.stream().map(countryToDto::map).collect(Collectors.toList());
    }

    /**
     * Get List of {@link Dish} from user
     * @param userNameOrEmail userName or email
     * @return List of {@link DishDTO}
     * @since 22/04/22
     */
    @Override
    public List<DishDTO> getUserDishes(String userNameOrEmail) {
        var user = findUser(userNameOrEmail);
        var dishes = user.getDishes();
        return dishes.stream().map(dishToDto::map).collect(Collectors.toList());
    }

    /**
     * Helper method find {@link User} from database
     * @param dto {@link UserDTO}
     * @return {@link User}
     * @throws UsernameNotFoundException if {@link User} with userName or email not exists in database
     * @since 22/04/22
     */
    private User findUser(UserDTO dto){
        return userRepository
                .findUserByUserNameOrEmail(dto.getUserNameOrEmail(), dto.getUserNameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User name or Email not found"));
    }

    /**
     * Helper method find {@link User} from database
     * @param userNameOrEmail userName or email
     * @return {@link User}
     * @throws UsernameNotFoundException if {@link User} with userName or email not exists in database
     * @since 22/04/22
     */
    private User findUser(String userNameOrEmail){
        return userRepository
                .findUserByUserNameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User name or Email not found"));
    }

    /**
     * Helper method find {@link Country} from database
     * @param id {@link Country} id
     * @return {@link Country}
     * @throws ResourceNotFoundException if {@link Country} with id not exists in database
     * @since 22/04/22
     */
    private Country findCountry(long id){
        return countryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Country", "id", id));
    }

    /**
     * Helper method find {@link Dish} from database
     * @param id {@link Dish} id
     * @return {@link Dish}
     * @throws ResourceNotFoundException if {@link Dish} with id not exists in database
     * @since 22/04/22
     */
    private Dish findDish(long id){
        return dishRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Dish", "id", id));
    }
}
