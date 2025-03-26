package com.olawhales.whales_ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
    private String IdGenerator;

    @ManyToOne
    @JoinColumn (name = "seller_id")
    @JsonBackReference
    private Seller seller;


    @Column(nullable = false)
    private String images ;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @ManyToOne
    private CartItem cartItem;  // This is what `mappedBy = "cartItem"` expects

    public String getIdGenerator() {
        return IdGenerator;
    }

    public void setIdGenerator(String idGenerator) {
        IdGenerator = idGenerator;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    private boolean isDeleted = false; // Soft delete

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
