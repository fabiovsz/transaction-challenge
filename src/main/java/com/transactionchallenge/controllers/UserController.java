package com.transactionchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.dto.user.CreateUserDTO;
import com.transactionchallenge.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO createUserDTO) {

        var user = User.builder()
            .firstName(createUserDTO.getFirstName())
            .lastName(createUserDTO.getLastName())
            .userType(createUserDTO.getUserType())
            .email(createUserDTO.getEmail())
            .password(createUserDTO.getPassword())
            .document(createUserDTO.getDocument())
            .balance(createUserDTO.getBalance())
            .build();

        var result = this.userService.createUser(user);
        return ResponseEntity.ok().body(result);
    }
    
}
