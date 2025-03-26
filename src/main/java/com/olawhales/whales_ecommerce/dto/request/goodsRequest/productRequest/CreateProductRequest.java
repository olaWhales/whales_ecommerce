package com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Data
public class CreateProductRequest {
//    private Long sellerId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;

    @Column()
    private MultipartFile images ;

//    public Long getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(Long sellerId) {
//        this.sellerId = sellerId;
//    }


    public MultipartFile getImages() {
        return images;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
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


