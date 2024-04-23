package com.transactionchallenge.exceptions;

public class TransactionNotAuthorizedException extends RuntimeException {
    public TransactionNotAuthorizedException() {
        super("Transaction not authorized");
    }
}
