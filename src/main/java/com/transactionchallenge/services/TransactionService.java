package com.transactionchallenge.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.transactionchallenge.domain.transaction.Transaction;
import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.domain.user.UserType;
import com.transactionchallenge.dto.transaction.CreateTransactionDTO;
import com.transactionchallenge.exceptions.ShopkeeperUserException;
import com.transactionchallenge.exceptions.TransactionNotAuthorizedException;
import com.transactionchallenge.exceptions.UnavailableBalanceException;
import com.transactionchallenge.repositories.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  EmailService emailService;

    @Transactional
    public Transaction createTransaction(CreateTransactionDTO transactionDTO) {
        var sender = this.userService.findUserById(transactionDTO.getSenderId());

        var receiver = this.userService.findUserById(transactionDTO.getReceiverId());

        this.validateTransaction(sender, transactionDTO.getAmount());

        var senderNewBalance = sender.getBalance().subtract(transactionDTO.getAmount());
        sender.setBalance(senderNewBalance);

        var receiverNewBalance = receiver.getBalance().add(transactionDTO.getAmount());
        receiver.setBalance(receiverNewBalance);

        var newTransaction = Transaction.builder()
            .sender(sender)
            .receiver(receiver)
            .amount(transactionDTO.getAmount())
            .build();
        var createdTransaction = this.transactionRepository.save(newTransaction);
        this.emailService.sendTransactionEmail(createdTransaction);

        return createdTransaction;
    }

    public boolean validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() == UserType.SHOPKEEPER) {
            throw new ShopkeeperUserException();
        }
        
        if (sender.getBalance().compareTo(amount) < 0 ) {
            throw new UnavailableBalanceException();
        }

        var authorizeTransactionUrl = "https://util.devi.tools/api/v2/authorize";
        var authorizeTransactionResponse = this.restTemplate.getForEntity(authorizeTransactionUrl, Map.class);

        if (!authorizeTransactionResponse.getBody().get("status").equals("success")) {
            throw new TransactionNotAuthorizedException();
        }

        return true;
    }
}
