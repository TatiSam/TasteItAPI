package com.tatisam.tasteit.repositories.app;

import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.entities.app.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with table ratings {@link com.tatisam.tasteit.entities.app.Rating} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 02/05/22
 */
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating getRatingByCountryAndIp(Country country, String ip);
}
