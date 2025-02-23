package com.olawhales.whales_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WhalesEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhalesEcommerceApplication.class, args);
	}

}
