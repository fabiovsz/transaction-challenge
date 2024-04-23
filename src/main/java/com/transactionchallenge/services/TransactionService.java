package com.transactionchallenge.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.transactionchallenge.domain.transaction.Transaction;
import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.domain.user.UserType;
import com.transactionchallenge.dto.CreateTransactionDTO;
import com.transactionchallenge.exceptions.ShopkeeperUserException;
import com.transactionchallenge.exceptions.TransactionNotAuthorizedException;
import com.transactionchallenge.exceptions.TransactionNotFoundException;
import com.transactionchallenge.exceptions.UnavailableBalanceException;
import com.transactionchallenge.exceptions.UserNotFoundException;
import com.transactionchallenge.repositories.TransactionRepository;
import com.transactionchallenge.repositories.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(CreateTransactionDTO transactionDTO) {
        var sender = this.userRepository.findById(transactionDTO.getSenderId()).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );

        var receiver = this.userRepository.findById(transactionDTO.getReceiverId()).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );

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
        
        return this.transactionRepository.save(newTransaction);
    }

    public boolean validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() == UserType.SHOPKEEPERS) {
            throw new ShopkeeperUserException();
        }
        
        if (sender.getBalance().compareTo(amount) < 0 ) {
            throw new UnavailableBalanceException();
        }

        var authorizeTransactionUrl = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        var authorizeTransactionResponse = this.restTemplate.getForEntity(authorizeTransactionUrl, Map.class);

        if (authorizeTransactionResponse.getBody().get("message") != "Autorizado") {
            throw new TransactionNotAuthorizedException();
        }

        return true;
    }

    public void reverseTransaction(UUID transactionId) {
        var transaction = this.transactionRepository.findById(transactionId).orElseThrow(
            () -> {
                throw new TransactionNotFoundException();
            }
        );

        var sender = this.userRepository.findById(transaction.getSender().getId()).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );

        var receiver = this.userRepository.findById(transaction.getReceiver().getId()).orElseThrow(
            () -> {
                throw new UserNotFoundException();
            }
        );
        
        var reversedSenderBalance = sender.getBalance().add(transaction.getAmount());
        sender.setBalance(reversedSenderBalance);

        var reversedReceiverBalance = receiver.getBalance().subtract(transaction.getAmount());
        receiver.setBalance(reversedReceiverBalance);

        this.transactionRepository.delete(transaction);
    }

}
