package com.transactionchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.dto.auth.AuthenticationDTO;
import com.transactionchallenge.dto.auth.LoginResponseDTO;
import com.transactionchallenge.dto.user.CreateUserDTO;
import com.transactionchallenge.infra.security.TokenService;
import com.transactionchallenge.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationDTO authDTO) {
        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthentication);
        var token = this.tokenService.generateToken((User)auth.getPrincipal());

        var loginResponse = LoginResponseDTO.builder()
            .token(token)
            .build();

        return ResponseEntity.ok().body(loginResponse);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody CreateUserDTO createUserDTO) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserDTO.getPassword());

        var user = User.builder()
            .firstName(createUserDTO.getFirstName())
            .lastName(createUserDTO.getLastName())
            .userType(createUserDTO.getUserType())
            .email(createUserDTO.getEmail())
            .password(encryptedPassword)
            .document(createUserDTO.getDocument())
            .balance(createUserDTO.getBalance())
            .build();

        var result = this.userService.createUser(user);
        return ResponseEntity.ok().body(result);
    }
}
