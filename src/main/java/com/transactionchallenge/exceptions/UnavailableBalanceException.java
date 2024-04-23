package com.transactionchallenge.exceptions;

public class UnavailableBalanceException extends RuntimeException {
    public UnavailableBalanceException() {
        super("The payer does not have a sufficient balance");
    }
}
