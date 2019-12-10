package com.sandro.cursojava.services;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sandro.cursojava.domain.Customer;
import com.sandro.cursojava.repository.CustomerRepository;
import com.sandro.cursojava.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Customer customer = customerRepository.findByEmail(email);

		if (Objects.isNull(customer)) {
			throw new ObjectNotFoundException("Ëmail não encontrado");
		}

		String newPassword = getNewPassword();
		customer.setPassword(pe.encode(newPassword));
		customerRepository.save(customer);
		emailService.sendNewPassword(customer, newPassword);
	}

	private String getNewPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChart();
		}
		return new String(vet);
	}

	private char randomChart() {
		int opt = rand.nextInt(3);

		
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiscula
			return (char) (rand.nextInt(26) + 65);
		} else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97);

		}
	}

}
