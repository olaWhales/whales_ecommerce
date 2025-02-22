package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import com.olawhales.whales_ecommerce.data.model.Seller;
import lombok.Data;

@Data
public class CreateProductRequest {
    private Long sellerId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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
}


