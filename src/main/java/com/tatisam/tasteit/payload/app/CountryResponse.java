package com.tatisam.tasteit.payload.app;

import lombok.*;

import java.util.List;

/**
 * The Data Access Object is used to response data about {@link com.tatisam.tasteit.entities.app.Country} from the database
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CountryResponse {
    private List<CountryDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}
