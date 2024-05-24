package com.example.test.utils.handler;

import com.example.test.exception.Error;
import com.example.test.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * A general exception handler that deals with runtime exception.
     *
     * @return A http 500 response
     */
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleGeneralException() {
        Error error =
              Error.builder()
                    .id(UUID.randomUUID().toString())
                    .message("Internal Server Error: System is currently unavailable.")
                    .name("INTERNAL_SERVER_ERROR")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                    .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Handle Resource Not Found Error.
     *
     * @param ex ResourceNotFoundException Exception
     * @return A Http 404 response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Error error =
              Error.builder()
                    .id(UUID.randomUUID().toString())
                    .message(ex.getMessage())
                    .name("NOT_FOUND_ERROR")
                    .status(HttpStatus.NOT_FOUND.toString())
                    .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handle Constraint Violation Exception.
     *
     * @param ex ConstraintViolationException Exception
     * @return A Http 400 response
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Error error =
              Error.builder()
                    .id(UUID.randomUUID().toString())
                    .message(ex.getMessage())
                    .name("VALIDATION ERROR")
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
