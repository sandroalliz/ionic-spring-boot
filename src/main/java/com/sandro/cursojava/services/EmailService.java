package com.sandro.cursojava.services;

import org.springframework.mail.SimpleMailMessage;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.Order;

public interface EmailService {

    void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage msg);
    
    void sendNewPassword(Customer customer, String newPassword);	
}
