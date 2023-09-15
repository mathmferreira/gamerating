package com.example.gamerating.exception.handler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String ENTITY_NOT_FOUND = "Entity not found";
    private static final String ENTITY_ALREADY_EXISTS = "Entity already exists";
    private static final String VALIDATION_CONSTRAINTS_FAILED = "Validation Constraints Failed";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ EntityNotFoundException.class, EmptyResultDataAccessException.class })
    public void entityNotFound(RuntimeException ex) {
        logThis(ENTITY_NOT_FOUND, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    public void entityExists(EntityExistsException ex) {
        logThis(ENTITY_ALREADY_EXISTS, ex);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logThis(VALIDATION_CONSTRAINTS_FAILED, ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private void logThis(String message, Exception ex) {
        log.debug(message, ex);
        if (!log.isDebugEnabled()) {
            log.warn(message);
        }
    }

}
