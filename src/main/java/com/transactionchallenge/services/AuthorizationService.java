package com.transactionchallenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.exceptions.UserNotFoundException;
import com.transactionchallenge.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userRecord = userRepository.findUserByEmail(email);
        
        if (!userRecord.isPresent()) {
            throw new UserNotFoundException();
        }
        
        return userRecord.get();
    }
    
}
