package com.transactionchallenge.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMailDTO {
    private String to;
    private String from;
    private String message;
    private String subject;
}
