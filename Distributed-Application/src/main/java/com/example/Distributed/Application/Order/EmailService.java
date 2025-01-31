package com.example.Distributed.Application.Order;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String userId) {
        System.out.println("Sending order confirmation email to user: " + userId);
    }
}

