package com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse;

import lombok.Data;

@Data
public class CreateCartItemResponse {
    private Long productId ;
    private Integer quantity ;
    private Double price ;
//    private String message ;
}
