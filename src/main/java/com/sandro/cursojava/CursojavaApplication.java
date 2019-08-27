package com.sandro.cursojava;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.domain.Product;
import com.sandro.cursojava.repository.CategoryRepository;
import com.sandro.cursojava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursojavaApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

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
	}
}
