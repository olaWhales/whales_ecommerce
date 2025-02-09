package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import com.olawhales.whales_ecommerce.data.model.Seller;
import lombok.Data;

@Data
public class CreateProductRequest {
    private Long sellerId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}


