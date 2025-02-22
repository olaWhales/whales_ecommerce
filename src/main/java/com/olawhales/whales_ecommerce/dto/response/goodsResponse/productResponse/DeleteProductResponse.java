package com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse;

import lombok.Data;

@Data
public class DeleteProductResponse {
    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
