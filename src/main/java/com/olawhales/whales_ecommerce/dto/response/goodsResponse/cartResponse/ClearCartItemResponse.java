package com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse;

import lombok.Data;

@Data
public class ClearCartItemResponse {
    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
