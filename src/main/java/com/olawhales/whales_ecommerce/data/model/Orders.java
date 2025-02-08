package com.olawhales.whales_ecommerce.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long orderId;

    private LocalDateTime orderDate;
    private Double totalAmount;

    @OneToMany (mappedBy = "orders", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();
}
