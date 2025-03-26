package com.olawhales.whales_ecommerce.SecurityConfig;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {
    public String generateId() {
        Random random = new Random();
        int number = random.nextInt(10000, 20000);
        return String.valueOf(number);
    }

    @Override
    public String toString() {
        return "GeneratedId: " + generateId();
    }
}
