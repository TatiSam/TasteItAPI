package com.tatisam.tasteit.exceptions.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an application tries to validate token with JWT - {@link com.auth0.jwt.JWT}
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenException extends RuntimeException{
    public TokenException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
