package com.transactionchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionchallenge.dto.CreateTransactionDTO;
import com.transactionchallenge.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Object> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        
        var result = this.transactionService.createTransaction(createTransactionDTO);
        return ResponseEntity.ok().body(result);
    }
    
}
