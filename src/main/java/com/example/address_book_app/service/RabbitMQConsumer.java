package com.example.address_book_app.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "emailQueue")
    public void processEmail(String message) {
        System.out.println("✅ Processing email in background: " + message);

        // Split the message to extract email & content
        String[] parts = message.split(", ");
        String to = parts[0].replace("To: ", "");
        String subject = parts[1].replace("Subject: ", "");
        String content = parts[2].replace("Message: ", "");

        sendEmail(to, subject, content);
    }

    private void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
        System.out.println("📨 Email sent to: " + to);
    }
}