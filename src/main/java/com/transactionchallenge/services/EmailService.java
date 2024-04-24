package com.transactionchallenge.services;

import com.transactionchallenge.exceptions.EmailServiceOfflineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.transactionchallenge.domain.transaction.Transaction;
import com.transactionchallenge.dto.email.SendMailDTO;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RestTemplate restTemplate;

    public void sendEmail(SendMailDTO sendMailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(sendMailDTO.to);
        message.setFrom(sendMailDTO.from);
        message.setSubject(sendMailDTO.subject);
        message.setText(sendMailDTO.message);

        emailSender.send(message);
    }

    public void sendTransactionEmail(Transaction transaction) {

        String message = String.format("You have just received a transfer from %s for $%.2f", transaction.getSender().getFirstName(), transaction.getAmount());

        var sendMailData = SendMailDTO.builder()
                .to(transaction.getReceiver().getEmail())
                .from("test@email.com")
                .message(message)
                .subject("Received transfer")
                .build();

        var emailServiceStatusUrl = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
        var emailServiceStatusResponse = this.restTemplate.getForEntity(emailServiceStatusUrl, Map.class);
        var emailServiceMessage = emailServiceStatusResponse.getBody().get("message").toString();
        
        if (Boolean.parseBoolean(emailServiceMessage) == false) {
            throw new EmailServiceOfflineException();
        }

        this.sendEmail(sendMailData);
    }
}