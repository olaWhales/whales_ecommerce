package com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse;

import lombok.Data;

@Data
public class CheckoutCartResponse {
    private CartResponse cartResponse;
    private String message;
}
