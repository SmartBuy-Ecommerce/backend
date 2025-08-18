package com.ShopMinds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String toEmail, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shimhan8531@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Account Created Successfully");
        message.setText("Hello " + userName + ",\n\nYour account has been created successfully! 🎉\n\nWelcome aboard!");
        mailSender.send(message);
    }
}
