package com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.Users;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateCartItemRequest {
    private String username ;
    private Long productId; // Linked product details
    private Integer quantity; // Quantity of product in cart

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
