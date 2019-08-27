package com.sandro.cursojava;

import com.sandro.cursojava.domain.Category;
import com.sandro.cursojava.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursojavaApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursojavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");

		categoryRepository.saveAll(Arrays.asList(category, category2));
	}
}
