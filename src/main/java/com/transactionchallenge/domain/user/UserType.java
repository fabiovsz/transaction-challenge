package com.transactionchallenge.domain.user;

public enum UserType {
    USER("USER"),
    SHOPKEEPER("SHOPKEEPER");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}