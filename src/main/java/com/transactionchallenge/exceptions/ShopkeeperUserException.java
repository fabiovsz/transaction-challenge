package com.transactionchallenge.exceptions;

public class ShopkeeperUserException extends RuntimeException {
    public ShopkeeperUserException() {
        super("Shopkeepers can not make transactions");
    }
    
}