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



	public static void main(String[] args) {
		SpringApplication.run(CursojavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
