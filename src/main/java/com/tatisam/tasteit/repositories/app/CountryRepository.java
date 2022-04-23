package com.tatisam.tasteit.repositories.app;

import com.tatisam.tasteit.entities.app.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for work with table countries {@link Country} from database
 * @implSpec JpaRepository
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    Boolean existsCountryByName(String name);
    Optional<Country> findCountryByName(String name);
    Optional<Country> findCountryByNameContains(String name);
}
