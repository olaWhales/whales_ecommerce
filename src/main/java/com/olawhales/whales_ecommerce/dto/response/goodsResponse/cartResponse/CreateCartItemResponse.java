package com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse;

import lombok.Data;

@Data
public class CreateCartItemResponse {
    private Long productId ;
    private Integer quantity ;
    private Double price ;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
