package com.olawhales.whales_ecommerce.dto.response.adminResponse;

import com.olawhales.whales_ecommerce.data.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ViewAllProductsResponse {
    private Long sellerUsername;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private String message;

    public Long getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(Long sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
