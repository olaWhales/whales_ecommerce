package com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse;

import lombok.Data;

@Data
public class ProductGetAllProductResponse {
    private Long sellerId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private String message;
}
