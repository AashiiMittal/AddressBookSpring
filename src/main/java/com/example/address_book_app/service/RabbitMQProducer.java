package com.example.address_book_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "emailQueue";

    public void sendEmailNotification(String to, String subject, String message) {
        String emailMessage = "To: " + to + ", Subject: " + subject + ", Message: " + message;
        rabbitTemplate.convertAndSend(QUEUE_NAME, emailMessage);
        System.out.println("📨 Email Request Sent to RabbitMQ: " + emailMessage);
    }
}