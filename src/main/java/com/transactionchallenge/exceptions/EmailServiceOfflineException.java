package com.transactionchallenge.exceptions;

public class EmailServiceOfflineException extends RuntimeException {
    public EmailServiceOfflineException() {
        super("Email service is offline");
    }
}