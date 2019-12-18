package com.sandro.cursojava.services;

import com.sandro.cursojava.domain.*;
import com.sandro.cursojava.domain.enums.CustomerType;
import com.sandro.cursojava.domain.enums.Profile;
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

        Product p12 = new Product(null, "Product 12", 10.00);
        Product p13 = new Product(null, "Product 13", 10.00);
        Product p14 = new Product(null, "Product 14", 10.00);
        Product p15 = new Product(null, "Product 15", 10.00);
        Product p16 = new Product(null, "Product 16", 10.00);
        Product p17 = new Product(null, "Product 17", 10.00);
        Product p18 = new Product(null, "Product 18", 10.00);
        Product p19 = new Product(null, "Product 19", 10.00);
        Product p20 = new Product(null, "Product 20", 10.00);
        Product p21 = new Product(null, "Product 21", 10.00);
        Product p22 = new Product(null, "Product 22", 10.00);
        Product p23 = new Product(null, "Product 23", 10.00);
        Product p24 = new Product(null, "Product 24", 10.00);
        Product p25 = new Product(null, "Product 25", 10.00);
        Product p26 = new Product(null, "Product 26", 10.00);
        Product p27 = new Product(null, "Product 27", 10.00);
        Product p28 = new Product(null, "Product 28", 10.00);
        Product p29 = new Product(null, "Product 29", 10.00);
        Product p30 = new Product(null, "Product 30", 10.00);
        Product p31 = new Product(null, "Product 31", 10.00);
        Product p32 = new Product(null, "Product 32", 10.00);
        Product p33 = new Product(null, "Product 33", 10.00);
        Product p34 = new Product(null, "Product 34", 10.00);
        Product p35 = new Product(null, "Product 35", 10.00);
        Product p36 = new Product(null, "Product 36", 10.00);
        Product p37 = new Product(null, "Product 37", 10.00);
        Product p38 = new Product(null, "Product 38", 10.00);
        Product p39 = new Product(null, "Product 39", 10.00);
        Product p40 = new Product(null, "Product 40", 10.00);
        Product p41 = new Product(null, "Product 41", 10.00);
        Product p42 = new Product(null, "Product 42", 10.00);
        Product p43 = new Product(null, "Product 43", 10.00);
        Product p44 = new Product(null, "Product 44", 10.00);
        Product p45 = new Product(null, "Product 45", 10.00);
        Product p46 = new Product(null, "Product 46", 10.00);
        Product p47 = new Product(null, "Product 47", 10.00);
        Product p48 = new Product(null, "Product 48", 10.00);
        Product p49 = new Product(null, "Product 49", 10.00);
        Product p50 = new Product(null, "Product 50", 10.00);

        category.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
           p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
           p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        p12.getCategories().add(category);
        p13.getCategories().add(category);
        p14.getCategories().add(category);
        p15.getCategories().add(category);
        p16.getCategories().add(category);
        p17.getCategories().add(category);
        p18.getCategories().add(category);
        p19.getCategories().add(category);
        p20.getCategories().add(category);
        p21.getCategories().add(category);
        p22.getCategories().add(category);
        p23.getCategories().add(category);
        p24.getCategories().add(category);
        p25.getCategories().add(category);
        p26.getCategories().add(category);
        p27.getCategories().add(category);
        p28.getCategories().add(category);
        p29.getCategories().add(category);
        p30.getCategories().add(category);
        p31.getCategories().add(category);
        p32.getCategories().add(category);
        p33.getCategories().add(category);
        p34.getCategories().add(category);
        p35.getCategories().add(category);
        p36.getCategories().add(category);
        p37.getCategories().add(category);
        p38.getCategories().add(category);
        p39.getCategories().add(category);
        p40.getCategories().add(category);
        p41.getCategories().add(category);
        p42.getCategories().add(category);
        p43.getCategories().add(category);
        p44.getCategories().add(category);
        p45.getCategories().add(category);
        p46.getCategories().add(category);
        p47.getCategories().add(category);
        p48.getCategories().add(category);
        p49.getCategories().add(category);
        p50.getCategories().add(category);

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
        productRepository.saveAll(Arrays.asList(product, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
           p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
           p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
        
        Customer customer2 = new Customer(null, "Ana Costa", "sandroalliz@hotmail.com", "77688570034", CustomerType.PESSOA_FISICA, be.encode("123"));
        customer2.addProfile(Profile.ADMIN);
        
        customer2.getPhones().addAll(Arrays.asList("26283135", "26283136"));

        Address address = new Address(null, "Rua Flores", "303", "Apto 203", "Jardim", "38220832", customer, city);
        Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", customer, city2);
        Address address3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "0463737", customer2, city2);

        customer.getAddresses().addAll(Arrays.asList(address, address2));
        customer2.getAddresses().addAll(Arrays.asList(address3));

        customerRepository.saveAll(Arrays.asList(customer, customer2));
        addressRepository.saveAll(Arrays.asList(address, address2, address3));

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
