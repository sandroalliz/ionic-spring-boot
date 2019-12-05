package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.*;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.domain.enums.StatusPayment;
import com.sandro.cursojava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder be;

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

    @Autowired
    ItemOrderRepository itemOrderRepository;

    public void instatiateDatabase() throws ParseException {

        Category category = new Category(null, "Informática");
        Category category2 = new Category(null, "Escritório");
        Category category3 = new Category(null, "Cama mesa e banho");
        Category category4 = new Category(null, "Eletrônicos");
        Category category5 = new Category(null, "Jardinagem");
        Category category6 = new Category(null, "Decoração");
        Category category7 = new Category(null, "Perfumaria");


        Product product = new Product(null, "Computador", 2000.00);
        Product product2 = new Product(null, "Impressora", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);
        Product product4 = new Product(null, "Mesa de escritório", 300.00);
        Product product5 = new Product(null, "Toalha", 50.00);
        Product product6 = new Product(null, "Colcha", 200.00);
        Product product7 = new Product(null, "TV true color", 1200.00);
        Product product8 = new Product(null, "Roçadeira", 800.00);
        Product product9 = new Product(null, "Abajour", 100.00);
        Product product10 = new Product(null, "Pendente", 180.00);
        Product product11 = new Product(null, "Shampoo", 90.00);

        category.getProducts().addAll(Arrays.asList(product, product2, product3));
        category2.getProducts().addAll(Arrays.asList(product2, product4));
        category3.getProducts().addAll(Arrays.asList(product5, product6));
        category4.getProducts().addAll(Arrays.asList(product, product2, product3, product7));
        category5.getProducts().addAll(Arrays.asList(product8));
        category6.getProducts().addAll(Arrays.asList(product9, product10));
        category7.getProducts().addAll(Arrays.asList(product11));

        product.getCategories().addAll((Arrays.asList(category, category4)));
        product2.getCategories().addAll((Arrays.asList(category, category2, category4)));
        product3.getCategories().addAll((Arrays.asList(category, category4)));
        product4.getCategories().addAll((Arrays.asList(category2)));
        product5.getCategories().addAll((Arrays.asList(category3)));
        product6.getCategories().addAll((Arrays.asList(category3)));
        product7.getCategories().addAll((Arrays.asList(category5)));
        product8.getCategories().addAll((Arrays.asList(category5)));
        product9.getCategories().addAll((Arrays.asList(category6)));
        product10.getCategories().addAll((Arrays.asList(category6)));
        product11.getCategories().addAll((Arrays.asList(category7)));


        categoryRepository.saveAll(Arrays.asList(category, category2, category3, category4, category5, category6, category7));
        productRepository.saveAll(Arrays.asList(product, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11));


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

        Customer customer = new Customer(null, "Maria Silva", "sandroalliz@gmail.com", "36378912377", CustomerType.PESSOA_FISICA, be.encode("123"));

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

        //ITEMS ORDERS
        ItemOrder item = new ItemOrder(order, product, 0.00, 1, 2000.00);
        ItemOrder item2 = new ItemOrder(order, product3, 0.00, 2, 80.00);
        ItemOrder item3 = new ItemOrder(order2, product2, 100.00, 1, 800.00);

        order.getItems().addAll(Arrays.asList(item, item2));
        order2.getItems().addAll(Arrays.asList(item3));

        product.getItems().addAll(Arrays.asList(item));
        product2.getItems().addAll(Arrays.asList(item3));
        product3.getItems().addAll(Arrays.asList(item2));

        itemOrderRepository.saveAll(Arrays.asList(item, item2, item3));
    }
}
