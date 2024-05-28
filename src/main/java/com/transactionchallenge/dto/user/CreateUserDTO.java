package com.transactionchallenge.dto.user;

import java.math.BigDecimal;

import com.transactionchallenge.domain.user.UserType;

import lombok.Data;



@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private UserType userType;
    private String email;
    private String password;
    private String document;
    private BigDecimal balance;
}
