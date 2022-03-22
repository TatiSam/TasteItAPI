package com.tatisam.tasteit.payload.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * The Data Access Object is used to request data about {@link com.tatisam.tasteit.entities.app.Comment}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/03/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDTO {
    private long id;

    @NotEmpty(message = "{com.tatisam.constraints.name.NotEmpty.message}")
    private String name;

    @NotEmpty(message = "{com.tatisam.constraints.email.NotEmpty.message}")
    private String email;

    @NotEmpty(message = "{com.tatisam.constraints.commentBody.NotEmpty.message}")
    private String body;
}
