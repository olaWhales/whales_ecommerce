package com.olawhales.whales_ecommerce.dto.request.adminRequest;

import lombok.Data;


@Data
public class ViewAllProductRequest {
//    private Long sellerUsername;
    private Long sellerId ;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
