package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest;

import lombok.Data;

@Data
public class ViewCartItemRequest {
    private String userName ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
