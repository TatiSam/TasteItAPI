package com.tatisam.tasteit.exceptions;

import com.tatisam.tasteit.exceptions.app.DuplicateResourceException;
import com.tatisam.tasteit.exceptions.app.ResourceNotFoundException;
import com.tatisam.tasteit.exceptions.app.UserResourcesException;
import com.tatisam.tasteit.exceptions.auth.RegisterException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;

/**
 * Controller for handling global exceptions
 * @author Tatiana Samoilenko
 * @version 1.0
 * @since 28/02/22
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException
     * @param ex {@link MethodArgumentNotValidException}
     * @param headers {@link HttpHeaders}
     * @param status {@link HttpStatus}
     * @param request {@link WebRequest}
     * @return {@link ResponseEntity} with status Bad Request and HashMap with errors
     * @since 28/02/22
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        var map = new HashMap<String, String>();
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String currentValue = map.get(fieldError.getField());
            if (currentValue == null)
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            else {
                map.put(fieldError.getField(), currentValue + ";" + fieldError.getDefaultMessage());
            }
        }
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentTypeMismatchException
     * @param ex {@link MethodArgumentTypeMismatchException}
     * @param request {@link WebRequest}
     * @return {@link ResponseEntity} with status Bad Request and {@link ErrorDetails}
     * @since 28/02/22
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle ResourceNotFoundException
     * @param ex {@link ResourceNotFoundException}
     * @return {@link ResponseEntity} with status Not Found and {@link ErrorDetails}
     * @since 28/02/22
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String details = String.format("Resource: %s Not Found. Field: %s, value: %s", ex.getResourceName(),
                ex.getFieldName(), ex.getFieldValue());
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(), details);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle UserResourcesException
     * @param ex {@link UserResourcesException}
     * @return {@link ResponseEntity} with status Forbidden and {@link ErrorDetails}
     * @since 22/04/22
     */
    @ExceptionHandler(UserResourcesException.class)
    public ResponseEntity<ErrorDetails> handleUserResourcesException(UserResourcesException ex) {
        String details = String.format("Failed for [%s]: %s. %s",
                ex.getResourceId(), ex.getResourceValue(), ex.getMessage());
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(), details);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle RegisterException
     * @param ex {@link RegisterException}
     * @return {@link ResponseEntity} with status Forbidden and {@link ErrorDetails}
     * @since 01/03/22
     */
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<ErrorDetails> handleRegisterException(RegisterException ex) {
        String details = String.format("Failed for %s[%s]: %s", ex.getFieldName(), ex.getFieldValue(), ex.getMessage());
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(), details);
        return new ResponseEntity(errorDetails, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle DuplicateResourceException
     * @param ex {@link DuplicateResourceException}
     * @return {@link ResponseEntity} with status Forbidden and {@link ErrorDetails}
     * @since 03/03/22
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateResourceException(DuplicateResourceException ex) {
        String details = String.format("Failed for %s[%s]: %s", ex.getFieldName(), ex.getFieldValue(), ex.getMessage());
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(), details);
        return new ResponseEntity(errorDetails, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle IllegalArgumentException
     * @param ex {@link IllegalArgumentException}
     * @param request {@link WebRequest}
     * @return {@link ResponseEntity} with status Bad Request and {@link ErrorDetails}
     * @since 03/03/22
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                                  WebRequest request) {
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle PropertyReferenceException
     * @param ex {@link PropertyReferenceException}
     * @param request {@link WebRequest}
     * @return {@link ResponseEntity} with status Bad Request and {@link ErrorDetails}
     * @since 03/03/22
     */
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorDetails> handlePropertyReferenceException(PropertyReferenceException ex,
                                                                       WebRequest request) {
        var errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
