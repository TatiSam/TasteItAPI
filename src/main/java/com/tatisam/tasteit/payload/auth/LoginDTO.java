package com.tatisam.tasteit.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The Data Access Object is used to request data during login
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @NotNull(message = "{com.tatisam.constraints.userNameOrEmail.NotNull.message}")
    private String userNameOrEmail;

    @NotNull(message = "{com.tatisam.constraints.password.NotNull.message}")
    private String password;
}
