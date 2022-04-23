package com.tatisam.tasteit.services.app;

import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.CountryResponse;

/**
 * Interface for implementing by Country Service
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
public interface CountryService {
    CountryDTO createCountry(CountryDTO dto);
    CountryResponse getAllCountries(int pageNo, int pageSize, String sortDir, String sortBy);
    CountryDTO getCountryById(long id);
    CountryDTO getCountryByName(String name);
    CountryDTO getRandomCountry();
    CountryDTO updateCountryById(long id, CountryDTO dto);
    CountryDTO addRatingToCountry(long id, int newRateValue);
    void deleteCountryById(long id);
}
