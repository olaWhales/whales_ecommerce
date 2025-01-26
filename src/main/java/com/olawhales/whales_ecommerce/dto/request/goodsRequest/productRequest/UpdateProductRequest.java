package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private Long id ;
    private Long sellerId ;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}
