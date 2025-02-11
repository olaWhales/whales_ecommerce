package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse;

import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CartResponse;
import lombok.Data;

@Data
public class CheckoutCartResponse {
    private Double totalPrice;
    private String message;
}
