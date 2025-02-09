package com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long userId ;
    private Long productId ;
    private Integer quantity ;
    private String message ;
}
