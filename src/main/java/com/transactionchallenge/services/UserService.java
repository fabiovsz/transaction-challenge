package com.transactionchallenge.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.exceptions.UserFoundException;
import com.transactionchallenge.exceptions.UserNotFoundException;
import com.transactionchallenge.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        var userRecord = this.userRepository.findUserByEmail(user.getEmail());

        if (userRecord.isPresent()) {
            throw new UserFoundException();
        }
        
        return this.userRepository.save(user);
    }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
    
}
