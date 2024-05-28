package com.transactionchallenge.dto.auth;

import lombok.Data;



@Data
public class AuthenticationDTO {
    private String email;
    private String password;
}
