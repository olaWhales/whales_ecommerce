package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import lombok.Data;

@Data
public class GetAllProductsRequest {
    private Long sellerId;


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
