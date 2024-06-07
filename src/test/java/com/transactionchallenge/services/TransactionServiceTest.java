package com.transactionchallenge.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.transactionchallenge.domain.user.User;
import com.transactionchallenge.dto.transaction.CreateTransactionDTO;
import com.transactionchallenge.repositories.TransactionRepository;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EmailService emailService;

    @Spy
    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create transaction succesfully when everything is OK")
    void createTransactionWhenEverythingIsOk() {
        var senderId = UUID.randomUUID();
        var sender = User.builder()
            .id(senderId)
            .firstName("John")
            .lastName("Doe")
            .document("99999999877")
            .email("johndoe@email.com")
            .password("123123")
            .balance(new BigDecimal(400))
            .build();
        
        var receiverId = UUID.randomUUID();
        var receiver = User.builder()
        .id(receiverId)
        .firstName("Luka")
        .lastName("Doe")
        .document("99999999876")
        .email("luka@email.com")
        .password("123123")
        .balance(new BigDecimal(200))
        .build();

        when(this.userService.findUserById(senderId)).thenReturn(sender);
        when(this.userService.findUserById(receiverId)).thenReturn(receiver);

        doNothing().when(this.transactionService).validateTransaction(any(), any());

        var createTransactionDTO = new CreateTransactionDTO(new BigDecimal(10), senderId, receiverId);
        var transaction = this.transactionService.createTransaction(createTransactionDTO);

        verify(this.transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(390));
        verify(this.userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(210));
        verify(this.userService, times(1)).saveUser(receiver);

        verify(this.emailService, times(1)).sendTransactionEmail(transaction);

    }
    
}
