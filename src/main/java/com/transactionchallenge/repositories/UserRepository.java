package com.transactionchallenge.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionchallenge.domain.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);    
    Optional<User> findUserByDocument(String document);
}
