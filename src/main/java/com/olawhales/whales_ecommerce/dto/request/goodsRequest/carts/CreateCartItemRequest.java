package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.Users;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;

@Data
public class CreateCartItemRequest {
//    private Long userId;

//    private Long cartId; // Reference to the Cart
    private Long productId; // Linked product details
    private Integer quantity; // Quantity of product in cart
//    private BigDecimal totalPrice; // Price * quantity
}
