package com.tatisam.tasteit.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * Object is used to describe the error details
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@AllArgsConstructor
@Getter
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}
