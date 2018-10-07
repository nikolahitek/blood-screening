package com.sorsix.bloodscreening.handler;

import com.sorsix.bloodscreening.handler.exception.DataBaseTransactionException;
import org.hibernate.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;

@RestControllerAdvice
public class ExceptionHandlers {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity onPropertyNotFound(PropertyNotFoundException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity onUsernameNotFound(UsernameNotFoundException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity onEntityExists(EntityExistsException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity onSecurityException(SecurityException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(DataBaseTransactionException.class)
    public ResponseEntity onDataBaseTransactionException(DataBaseTransactionException e) {
        logger.warn(e.getMessage());
        return ResponseEntity.noContent().build();
    }

}
