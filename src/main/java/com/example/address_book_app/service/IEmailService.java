package com.example.address_book_app.service;

public interface IEmailService {
    public void sendEmail(String toEmail, String subject, String body);
}
