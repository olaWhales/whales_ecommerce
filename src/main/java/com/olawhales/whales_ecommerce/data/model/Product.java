package com.olawhales.whales_ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn (name = "seller_id")
    @JsonBackReference
    private Seller seller;

}
