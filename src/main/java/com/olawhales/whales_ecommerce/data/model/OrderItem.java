package com.olawhales.whales_ecommerce.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deliveryDate ;
    private LocalDateTime orderTime ;

    private Integer quantity ;
    private Double price ;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address ;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // ✅ Ensure it references Order
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
