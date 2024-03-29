package com.sandro.cursojava.config;

import com.sandro.cursojava.services.DBService;
import com.sandro.cursojava.services.EmailService;
import com.sandro.cursojava.services.SmtpMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if(!strategy.equals("create")){
            return false;
        }

        dbService.instatiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpMailService();
    }
}
