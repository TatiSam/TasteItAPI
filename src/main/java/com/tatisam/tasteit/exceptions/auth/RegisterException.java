package com.tatisam.tasteit.exceptions.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a user, upon registration, sends a username or email that is already registered in the database
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RegisterException extends RuntimeException{
    private final String fieldName;
    private final Object fieldValue;
    private final String message;
    public RegisterException(String fieldName, String fieldValue, String message) {
        super(String.format("Failed %s[%s]: %s",fieldName, fieldValue, message));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = message;
    }
}
