package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import com.olawhales.whales_ecommerce.data.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCartItemRequest {
    private Long userId;

    private Long id;
    private Long cartId; // Reference to the Cart
    private Product productId; // Linked product details
    private Integer quantity; // Quantity of product in cart
    private BigDecimal totalPrice; // Price * quantity
}
