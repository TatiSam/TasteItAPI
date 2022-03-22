package com.tatisam.tasteit.payload.app;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * The Data Access Object is used to request data about {@link com.tatisam.tasteit.entities.app.Dish}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DishDTO {
    private long id;

    @NotEmpty(message = "{com.tatisam.constraints.dishName.NotNull.message}")
    @Size(min = 2, message = "{com.tatisam.constraints.dishName.Size.message}")
    private String name;

    @NotEmpty(message = "{com.tatisam.constraints.dishArticle.NotNull.message}")
    @Size(min = 10, message = "{com.tatisam.constraints.dishArticle.Size.message}")
    private String article;

    @NotEmpty(message = "{com.tatisam.constraints.dishImgPath.NotNull.message}")
    @Size(min = 6, message = "{com.tatisam.constraints.dishImgPath.Size.message}")
    private String imgPath;
}
