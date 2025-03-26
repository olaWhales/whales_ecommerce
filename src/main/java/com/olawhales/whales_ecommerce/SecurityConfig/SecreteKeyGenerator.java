package com.olawhales.whales_ecommerce.SecurityConfig;

import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecreteKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHa256");
        keyGen.init(256); // Use 256-bit key
        SecretKey secretKey = keyGen.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Generated Secret Key: " + encodedKey);
    }
}