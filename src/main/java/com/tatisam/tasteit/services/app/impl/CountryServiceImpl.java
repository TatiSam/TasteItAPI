package com.tatisam.tasteit.services.app.impl;

import com.tatisam.tasteit.entities.app.Country;
import com.tatisam.tasteit.exceptions.app.DuplicateResourceException;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.payload.app.CountryDTO;
import com.tatisam.tasteit.payload.app.CountryResponse;
import com.tatisam.tasteit.repositories.app.CountryRepository;
import com.tatisam.tasteit.services.app.CountryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Country Service is used to work with {@link Country} from database
 * @implSpec {@link CountryService}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final TypeMap<CountryDTO, Country> toCountry;
    private final TypeMap<Country, CountryDTO> toDto;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        toCountry = modelMapper.createTypeMap(CountryDTO.class, Country.class);
        toDto = modelMapper.createTypeMap(Country.class, CountryDTO.class);
    }

    /**
     * Create {@link Country} in database
     * @param dto {@link CountryDTO}
     * @return {@link CountryDTO} from created {@link Country}
     * @throws DuplicateResourceException if {@link Country} already exists in database
     * @since 03/03/22
     */
    @Override
    public CountryDTO createCountry(CountryDTO dto) {
        if(countryRepository.existsCountryByName(dto.getName())){
            throw new DuplicateResourceException("countryName", dto.getName(), "Country already exists");
        }
        var newCountry = toCountry.map(dto);
        var saved = countryRepository.save(newCountry);
        return toDto.map(saved);
    }

    /**
     * Get List of {@link Country} from database using page size, page number, sort by field and sort direction
     * @param pageNo number of page
     * @param pageSize size of page
     * @param sortDir direction of sort
     * @param sortBy field of Country to sort by this field
     * @return {@link CountryResponse}
     * @since 03/03/22
     */
    @Override
    public CountryResponse getAllCountries(int pageNo, int pageSize, String sortDir, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<Country> countriesPage = countryRepository.findAll(pageable);
        List<CountryDTO> content = countriesPage.getContent().stream().map(toDto::map).collect(Collectors.toList());

        return CountryResponse.builder()
                .content(content)
                .pageSize(countriesPage.getSize())
                .pageNo(countriesPage.getNumber())
                .totalElements(countriesPage.getTotalElements())
                .totalPages(countriesPage.getTotalPages())
                .isLast(countriesPage.isLast())
                .build();
    }

    /**
     * Get {@link Country} by id
     * @param id {@link Country} id
     * @return {@link CountryDTO}
     * @since 03/03/22
     */
    @Override
    public CountryDTO getCountryById(long id) {
        return toDto.map(getCountryEntityById(id));
    }

    /**
     * Update {@link Country} in database by id
     * @param id {@link Country} id
     * @param dto {@link CountryDTO}
     * @return {@link CountryDTO}
     * @since 03/03/22
     */
    @Override
    public CountryDTO updateCountryById(long id, CountryDTO dto) {
        Country country = getCountryEntityById(id);
        country.setName(dto.getName());
        country.setArticle(dto.getArticle());
        country.setImgPath(dto.getImgPath());
        return toDto.map(countryRepository.save(country));
    }

    /**
     * Delete {@link Country} from database by id
     * @param id {@link Country} id
     * @since 03/03/22
     */
    @Override
    public void deleteCountryById(long id) {
        countryRepository.delete(getCountryEntityById(id));
    }

    /**
     * Helper method gets Country from database by id
     * @param id Country id
     * @return {@link Country}
     * @throws ResourceNotFoundException if Country not exists in database
     * @since 03/03/22
     */
    private Country getCountryEntityById(long id){
        return countryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Country", "id", id));
    }
}
