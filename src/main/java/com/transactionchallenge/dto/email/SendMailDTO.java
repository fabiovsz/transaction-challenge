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
    public String to;
    public String from;
    public String message;
    public String subject;
}
