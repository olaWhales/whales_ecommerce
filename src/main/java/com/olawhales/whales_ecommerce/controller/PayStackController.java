package com.olawhales.whales_ecommerce.controller;

//public class PayStackController {
//}
import com.olawhales.whales_ecommerce.dto.request.paymentRequest.PaystackPaymentRequest;
import com.olawhales.whales_ecommerce.paymentService.PaystackServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/paystack")
public class PayStackController {

    private final PaystackServiceImp paystackServiceImp;

    public PayStackController(PaystackServiceImp paystackServiceImp) {
        this.paystackServiceImp = paystackServiceImp;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaystackPaymentRequest request) {
        String paymentUrl = paystackServiceImp.initiatePayment(request);
        return ResponseEntity.ok(paymentUrl); // Frontend redirects user to this URL
    }

    @GetMapping("/verify/{reference}")
    public ResponseEntity<String> verifyPayment(@PathVariable String reference) {
        boolean isVerified = paystackServiceImp.verifyPayment(reference);
        return isVerified ? ResponseEntity.ok("Payment Successful!") : ResponseEntity.badRequest().body("Payment Failed!");
    }

    @RequestMapping("/api/paystack/webhook")
    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Paystack Webhook: " + payload);
        return ResponseEntity.ok("Webhook received");
    }

    @Override
    public String toString() {
        return "PayStackController{" +
                "paystackService=" + paystackServiceImp +
                '}';
    }
}
