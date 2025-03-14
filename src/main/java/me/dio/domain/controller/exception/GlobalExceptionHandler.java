package me.dio.domain.controller.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Trata exceções específicas de negócio
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException){
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    // Trata NoSuchElementException especificamente
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){
        return new ResponseEntity<>("Resource ID not found", HttpStatus.NOT_FOUND);
    }
    
    // Trata exceções inesperadas (gerais)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedExcepion(Throwable unexpectedExcepion){
        var message = "Unexpected server error, see the logs.";
        logger.error("Unexpected error: ", unexpectedExcepion);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
