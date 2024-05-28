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
        
        message.setTo(sendMailDTO.getTo());
        message.setFrom(sendMailDTO.getFrom());
        message.setSubject(sendMailDTO.getSubject());
        message.setText(sendMailDTO.getMessage());

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


        //Obs: Url is not available

        // var emailServiceStatusUrl = "https://util.devi.tools/api/v1/notify";
        // var emailServiceStatusResponse = this.restTemplate.getForEntity(emailServiceStatusUrl, Map.class);
        // var emailServiceMessage = emailServiceStatusResponse.getBody().get("message").toString();
        
        // if (Boolean.parseBoolean(emailServiceMessage) == false) {
        //     throw new EmailServiceOfflineException();
        // }

        this.sendEmail(sendMailData);
    }
}