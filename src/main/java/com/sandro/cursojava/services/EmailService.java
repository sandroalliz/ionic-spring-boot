package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage msg);
}
