package com.tatisam.tasteit.exceptions.app;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when resource already exists in database
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 03/03/22
 */
@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicateResourceException extends RuntimeException{
    private final String fieldName;
    private final Object fieldValue;
    private final String message;
    public DuplicateResourceException(String fieldName, String fieldValue, String message) {
        super(String.format("Failed %s[%s]: %s",fieldName, fieldValue, message));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.message = message;
    }
}
