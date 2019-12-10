package com.sandro.cursojava.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Order order) {
		SimpleMailMessage msg = prepareSimpleMailMessageFromOrder(order);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}

	@Override
	public void sendNewPassword(Customer customer, String newPassword) {
		SimpleMailMessage sm = buildEmailNewPassword(customer, newPassword);
		sendEmail(sm);
	}

	protected SimpleMailMessage buildEmailNewPassword(Customer customer, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(customer.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPassword);
		return sm;
	}
}
