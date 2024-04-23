package com.transactionchallenge.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactionchallenge.domain.transaction.Transaction;
import com.transactionchallenge.dto.CreateTransactionDTO;
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

        if (sender.getBalance().compareTo(transactionDTO.getAmount()) < 0 ) {
            throw new UnavailableBalanceException();
        }

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
