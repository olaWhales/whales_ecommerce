package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import lombok.Data;

@Data
public class ClearCartItemRequest {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
