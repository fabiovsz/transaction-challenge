package com.transactionchallenge.exceptions;

public class GenerationTokenException extends RuntimeException {
    public GenerationTokenException() {
        super("Error while generating token");
    }
}