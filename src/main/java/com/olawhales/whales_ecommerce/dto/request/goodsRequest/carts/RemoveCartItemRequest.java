package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import lombok.Data;

@Data
public class RemoveCartItemRequest {
    private Long cartId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
