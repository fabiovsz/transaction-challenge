package com.transactionchallenge.infra;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private HttpStatus statusCode;
    private String message;
}
