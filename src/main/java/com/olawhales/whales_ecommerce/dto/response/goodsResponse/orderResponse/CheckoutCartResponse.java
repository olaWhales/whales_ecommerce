package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse;

import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CartResponse;
import lombok.Data;

@Data
public class CheckoutCartResponse {
    private Double totalPrice;
    private String message;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
