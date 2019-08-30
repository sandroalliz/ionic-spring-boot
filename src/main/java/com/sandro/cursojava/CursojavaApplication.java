package com.sandro.cursojava;

import com.sandro.cursojava.domain.*;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.domain.enums.StatusPayment;
import com.sandro.cursojava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursojavaApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	PaymentRepository paymentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursojavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");

		Product product = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null,"Impressora", 800.00);
		Product product3 = new Product(null,"Mouse", 80.00);

		category.getProducts().addAll(Arrays.asList(product, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));

		product.getCategories().addAll((Arrays.asList(category)));
		product2.getCategories().addAll((Arrays.asList(category, category2)));
		product3.getCategories().addAll((Arrays.asList(category)));

		categoryRepository.saveAll(Arrays.asList(category, category2));
		productRepository.saveAll(Arrays.asList(product, product2, product3));


		//STATES AND CITIES

		State state = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city = new City(null, "Uberlandia", state);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state.getCities().addAll(Arrays.asList(city));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state, state2));
		cityRepository.saveAll(Arrays.asList(city, city2, city3));

		//CUSTOMER AND ADDRESSES

		Customer customer = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.PESSOA_FISICA);

		customer.getPhones().addAll(Arrays.asList("26283135", "26283136"));

		Address address = new Address(null, "Rua Flores", "303", "Apto 203", "Jardim", "38220832", customer, city);
		Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", customer, city2);

		customer.getAddresses().addAll(Arrays.asList(address, address2));

		customerRepository.saveAll(Arrays.asList(customer));
		addressRepository.saveAll(Arrays.asList(address, address2));

		//ORDERS

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order order = new Order(null, sdf.parse("30/09/2017 10:32"), customer, address);
		Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), customer, address2);

		Payment payment = new PaymentWithCard(null, StatusPayment.QUITADO, order, 6);
		order.setPayment(payment);

		Payment payment2 = new PaymentWithBankTicket(null, StatusPayment.PENDENTE, order2, sdf.parse("20/10/2017 00:00"), null);
		order2.setPayment(payment2);

		customer.getOrders().addAll(Arrays.asList(order, order2));

		orderRepository.saveAll(Arrays.asList(order, order2));
		paymentRepository.saveAll(Arrays.asList(payment, payment2));
	}
}
