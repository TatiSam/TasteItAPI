package com.tatisam.tasteit.payload.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The Data Access Object is used to request data from user
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 24/04/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NotNull(message = "{com.tatisam.constraints.userNameOrEmail.NotNull.message}")
    private String userNameOrEmail;
}
