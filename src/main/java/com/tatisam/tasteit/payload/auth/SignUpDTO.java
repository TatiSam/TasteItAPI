package com.tatisam.tasteit.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The Data Access Object is used to request data during registration
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {
    @NotNull(message = "{com.tatisam.constraints.username.NotNull.message}")
    @Size(min = 2, max = 24, message = "{com.tatisam.constraints.username.Size.message}")
    private String userName;

    @NotNull(message = "{com.tatisam.constraints.email.NotNull.message}")
    @Email(message = "{com.tatisam.constraints.email.Pattern.message}")
    private String email;

    @NotNull(message = "{com.tatisam.constraints.password.NotNull.message}")
    @Size(min = 6, max = 24, message = "{com.tatisam.constraints.password.Size.message}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{com.tatisam.constraints.password.Pattern.message}"
    )
    private String password;
}
