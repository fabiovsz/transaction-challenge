package com.transactionchallenge.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.transactionchallenge.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;

    @ManyToOne()
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private LocalDateTime timestamp;

}
