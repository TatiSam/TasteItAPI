package com.tatisam.tasteit.controllers.app;

import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.CountryResponse;
import com.tatisam.tasteit.services.app.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * RestController for work with {@link CountryService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@RestController
@RequestMapping("api/1/countries")
@CrossOrigin(origins = {"http://localhost:8080"})
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Add valid {@link com.tatisam.tasteit.entities.app.Country} to database
     * @param dto {@link CountryDTO}
     * @return {@link ResponseEntity} with status Created and {@link CountryDTO}
     * @since 03/03/22
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryDTO dto){
        return new ResponseEntity<>(countryService.createCountry(dto), HttpStatus.CREATED);
    }

    /**
     * Get List of {@link com.tatisam.tasteit.entities.app.Country} from {@link CountryService}
     * using page size, page number, sort by field and sort direction
     * @param pageNo number of page
     * @param pageSize size of page
     * @param sortDir direction of sort
     * @param sortBy field of Country to sort by this field
     * @return {@link ResponseEntity} with status Ok and List of {@link CountryDTO}
     * @since 03/03/22
     */
    @GetMapping("/page")
    public ResponseEntity<CountryResponse> getAllCountries(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ){
        pageSize = Math.min(25, pageSize);
        return ResponseEntity.ok()
                .body(countryService.getAllCountries(pageNo, pageSize, sortDir, sortBy));
    }

    /**
     * Get {@link com.tatisam.tasteit.entities.app.Country} by id
     * @param id {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and {@link CountryDTO}
     * @since 03/03/22
     */
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable long id){
        return ResponseEntity.ok().body(countryService.getCountryById(id));
    }

    /**
     * Get {@link com.tatisam.tasteit.entities.app.Country} by name
     * @param name {@link com.tatisam.tasteit.entities.app.Country} name
     * @return {@link ResponseEntity} with status Ok and {@link CountryDTO}
     * @since 22/04/22
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<CountryDTO> getCountryByName(@PathVariable String name){
        return ResponseEntity.ok().body(countryService.getCountryByName(name));
    }

    /**
     * Get random {@link com.tatisam.tasteit.entities.app.Country}
     * @return {@link ResponseEntity} with status Ok and random {@link CountryDTO}
     * @since 22/04/22
     */
    @GetMapping("/random")
    public ResponseEntity<CountryDTO> getRandomCountry(){
        return ResponseEntity.ok().body(countryService.getRandomCountry());
    }

    /**
     * Update {@link com.tatisam.tasteit.entities.app.Country} by id
     * @param id {@link com.tatisam.tasteit.entities.app.Country} id
     * @param dto {@link CountryDTO}
     * @return {@link ResponseEntity} with status Ok and updated {@link CountryDTO}
     * @since 03/03/22
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountryById(
            @PathVariable long id,
            @Valid @RequestBody CountryDTO dto){
        return ResponseEntity.ok().body(countryService.updateCountryById(id, dto));
    }

    /**
     * Delete {@link com.tatisam.tasteit.entities.app.Country} by id
     * @param id {@link com.tatisam.tasteit.entities.app.Country} id
     * @return {@link ResponseEntity} with status Ok and {@link String} with message
     * @since 03/03/22
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable long id){
        countryService.deleteCountryById(id);
        return ResponseEntity.ok().body("Country deleted successfully");
    }

    /**
     * Add rating to  {@link com.tatisam.tasteit.entities.app.Country}
     * @param id {@link com.tatisam.tasteit.entities.app.Country} id
     * @param newRateValue rating value from user
     * @return {@link ResponseEntity} with status Ok and updated {@link CountryDTO}
     * @since 22/04/22
     */
    @PostMapping("/{id}/rating/{newRateValue}")
    public ResponseEntity<CountryDTO> addRatingToCountry(@PathVariable("id") long id,
                                              @PathVariable("newRateValue") int newRateValue){
        return ResponseEntity.ok().body(countryService.addRatingToCountry(id, newRateValue));
    }
}
