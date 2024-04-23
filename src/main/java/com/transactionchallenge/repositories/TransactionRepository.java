package com.transactionchallenge.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transactionchallenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
