package com.transactionchallenge.dto.transaction;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;



@Data
public class CreateTransactionDTO {
    private BigDecimal amount;
    private UUID senderId;
    private UUID receiverId;
}
