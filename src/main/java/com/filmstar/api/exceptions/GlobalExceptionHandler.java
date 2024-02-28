package com.filmstar.api.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleMovieNotFoundException(MovieNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            "Movie not found",
            ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            "URL not found",
            ex.getRequestURL());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(RequestRejectedException.class)
    public ResponseEntity<ErrorDetails> handleRequestRejectedException(RequestRejectedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            "Error en la solicitud: " + ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
            "Error interno del servidor",
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}