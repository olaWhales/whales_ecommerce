package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.CheckoutCartResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    CheckoutCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest);

}
