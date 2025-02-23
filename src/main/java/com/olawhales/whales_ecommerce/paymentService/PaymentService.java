package com.olawhales.whales_ecommerce.paymentService;

import com.olawhales.whales_ecommerce.dto.request.paymentRequest.PaystackPaymentRequest;
import com.olawhales.whales_ecommerce.dto.response.paymentResponse.PaystackPaymentResponse;

public interface PaymentService {
    PaystackPaymentResponse initiatePayment(PaystackPaymentRequest Payment);
}
