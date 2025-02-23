package com.olawhales.whales_ecommerce;

import com.olawhales.whales_ecommerce.emailSpringEventPackage.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmailTesting {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EmailTesting.class, args);
        EmailService emailService = context.getBean(EmailService.class);

        emailService.sendEmail("ajaditaoreed@gmail.com", "Test Email", "This is a test email from Spring Boot!");
    }
}