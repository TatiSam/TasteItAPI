package com.tatisam.tasteit.payload.app;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * The Data Access Object is used to send {@link com.tatisam.tasteit.entities.app.Rating}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 02/05/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RatingDTO {
    @NotEmpty(message = "{com.tatisam.constraints.ip.NotEmpty.message}")
    private String ip;

    @Range(min = 1, max = 5, message = "{com.tatisam.constraints.rating.Size.message}")
    private int rating;
}
