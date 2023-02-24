package com.server.exception.handler;

import com.server.exception.UserExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String CREDENTIALS_MESSAGE = "Bad credentials";

    @ExceptionHandler({AuthenticationException.class, ExpiredJwtException.class})
    public ResponseEntity<Object> handleAuthenticationException(Exception ex) {
        return givenAuthenticationResponse(ex.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException exception) {
        return givenResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException exception) {
        return givenResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> givenAuthenticationResponse(String message) {
        if (message.equals(CREDENTIALS_MESSAGE)) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> givenResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}