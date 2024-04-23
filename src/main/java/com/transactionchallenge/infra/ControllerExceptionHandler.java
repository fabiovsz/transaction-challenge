package com.transactionchallenge.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.transactionchallenge.exceptions.TransactionNotFoundException;
import com.transactionchallenge.exceptions.UnavailableBalanceException;
import com.transactionchallenge.exceptions.UserFoundException;
import com.transactionchallenge.exceptions.UserNotFoundException;


@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserFoundException.class)
    private ResponseEntity userFoundHandler(UserFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity userNotFoundHandler(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(UnavailableBalanceException.class)
    private ResponseEntity unavailableBalanceHandler(UnavailableBalanceException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    private ResponseEntity transactionNotFoundHandler(TransactionNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}