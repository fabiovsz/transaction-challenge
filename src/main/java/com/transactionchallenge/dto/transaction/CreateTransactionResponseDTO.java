package com.transactionchallenge.dto.transaction;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTransactionResponseDTO {
    private BigDecimal amount;
    private String senderName;
    private String receiverName;
}
