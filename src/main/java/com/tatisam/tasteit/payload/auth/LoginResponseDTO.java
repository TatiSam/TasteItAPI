package com.tatisam.tasteit.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Data Access Object is used to response data from the server after login
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginResponseDTO {
    private String userNameOrEmail;
    private String jwt;
}
