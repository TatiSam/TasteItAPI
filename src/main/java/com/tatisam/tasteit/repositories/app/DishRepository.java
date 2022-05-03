package com.tatisam.tasteit.repositories.app;

import com.tatisam.tasteit.entities.app.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with table dishes {@link com.tatisam.tasteit.entities.app.Dish} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findDishesByCountryId(long id);
}
