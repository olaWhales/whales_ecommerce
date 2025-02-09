package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import com.olawhales.whales_ecommerce.data.model.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartRequest {
    private Long id;
    private Long userId; // Reference to the User
    private List<CartItem> cartItems; // List of cart items
    private BigDecimal totalPrice;
}
