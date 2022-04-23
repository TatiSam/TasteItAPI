package com.tatisam.tasteit.exceptions.app;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown in time working with user resources
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 22/04/22
 */
@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserResourcesException extends RuntimeException{
    private final String message;
    private final String resourceId;
    private final Object resourceValue;
    public UserResourcesException(String message, String resourceId, Object resourceValue) {
        super(String.format("Failed for [%s]: %s",resourceId, resourceValue, message));
        this.message = message;
        this.resourceId = resourceId;
        this.resourceValue = resourceValue;
    }
}
