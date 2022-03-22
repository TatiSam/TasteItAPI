package com.tatisam.tasteit.payload.app;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * The Data Access Object is used to request data about {@link com.tatisam.tasteit.entities.app.Country}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CountryDTO {
    private long id;

    @NotEmpty(message = "{com.tatisam.constraints.countryName.NotNull.message}")
    @Size(min = 2, message = "{com.tatisam.constraints.countryName.Size.message}")
    private String name;

    @NotEmpty(message = "{com.tatisam.constraints.countryArticle.NotNull.message}")
    @Size(min = 10, message = "{com.tatisam.constraints.countryArticle.Size.message}")
    private String article;

    @NotEmpty(message = "{com.tatisam.constraints.countryImgPath.NotNull.message}")
    @Size(min = 6, message = "{com.tatisam.constraints.countryImgPath.Size.message}")
    private String imgPath;

    private Set<DishDTO> dishes;

    private Set<CommentDTO> comments;
}
