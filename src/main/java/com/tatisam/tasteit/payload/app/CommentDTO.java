package com.tatisam.tasteit.payload.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    private Date date;

    @NotEmpty(message = "{com.tatisam.constraints.name.NotEmpty.message}")
    private String authorName;

    @NotEmpty(message = "{com.tatisam.constraints.email.NotEmpty.message}")
    private String authorEmail;

    @NotEmpty(message = "{com.tatisam.constraints.commentBody.NotEmpty.message}")
    private String body;
}
