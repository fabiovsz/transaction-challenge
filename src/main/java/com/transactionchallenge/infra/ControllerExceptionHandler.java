package com.transactionchallenge.infra;

import com.transactionchallenge.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserFoundException.class)
    private ResponseEntity userFoundHandler(UserFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity userNotFoundHandler(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(UnavailableBalanceException.class)
    private ResponseEntity unavailableBalanceHandler(UnavailableBalanceException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    private ResponseEntity transactionNotFoundHandler(TransactionNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(ShopkeeperUserException.class)
    private ResponseEntity shopkeeperUserHandler(ShopkeeperUserException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    private ResponseEntity transactionNotAuthorizedHandler(TransactionNotAuthorizedException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(EmailServiceOfflineException.class)
    private ResponseEntity emailServiceOfflineHandler(EmailServiceOfflineException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity runtimeHandler(RuntimeException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(GenerationTokenException.class)
    private ResponseEntity generationTokenHandler(GenerationTokenException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}