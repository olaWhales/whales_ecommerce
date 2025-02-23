package com.olawhales.whales_ecommerce.paymentService;

import com.olawhales.whales_ecommerce.dto.request.paymentRequest.PaystackPaymentRequest;
import com.olawhales.whales_ecommerce.dto.response.paymentResponse.PaystackPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PaystackServiceImp {

    private static final String PAYSTACK_SECRET_KEY = "sk_test_a69c924b88ff83ba44a6668354bf4f766759d3d2";
    private static final String PAYSTACK_INIT_URL = "https://api.paystack.co/transaction/initialize";
    private static final String PAYSTACK_VERIFY_URL = "https://api.paystack.co/transaction/verify/";

   @Autowired
   private RestTemplate restTemplate;

    public String initiatePayment(PaystackPaymentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", PAYSTACK_SECRET_KEY);

        HttpEntity<PaystackPaymentRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<PaystackPaymentResponse> response = restTemplate.exchange(PAYSTACK_INIT_URL, HttpMethod.POST, entity, PaystackPaymentResponse.class);

        if (response.getBody() != null && response.getBody().isStatus()) {
            return response.getBody().getData().getAuthorization_url();
        } else {
            throw new RuntimeException("Payment initialization failed!");
        }
    }

    public boolean verifyPayment(String reference) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", PAYSTACK_SECRET_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<PaystackPaymentResponse> response = restTemplate.exchange(PAYSTACK_VERIFY_URL + reference, HttpMethod.GET, entity, PaystackPaymentResponse.class);

        return response.getBody() != null && response.getBody().isStatus();
    }
}
