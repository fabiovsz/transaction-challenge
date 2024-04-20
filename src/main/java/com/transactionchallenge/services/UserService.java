package com.transactionchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.exceptions.UserFoundException;
import com.transactionchallenge.repositories.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        this.userRepository.findUserByEmail(user.getEmail())
            .ifPresent((userRecord) -> {
                throw new UserFoundException();
            });
        
        return this.userRepository.save(user);
    }

    
}
