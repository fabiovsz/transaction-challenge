package com.transactionchallenge.dto;

import org.hibernate.usertype.UserType;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private UserType userType;
    private String email;
    private String password;
    private String document;
}
