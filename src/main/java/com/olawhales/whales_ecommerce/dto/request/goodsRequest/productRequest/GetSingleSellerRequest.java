package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import lombok.Data;

@Data
public class GetSingleSellerRequest {
    private Long sellerId ;
    private Long productId ;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
