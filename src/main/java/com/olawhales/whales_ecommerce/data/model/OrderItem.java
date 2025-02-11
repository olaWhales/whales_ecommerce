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
//    @NotNull
//    @Size(min = 2, max = 50)
    private Integer quantity ;
    private Double price ;

//    @Enumerated(EnumType.STRING)
//    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address ;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // ✅ Ensure it references Order
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    @ManyToMany
//    @JoinTable(
//            name = "order_item_product",
//            joinColumns = @JoinColumn (name = "order_item_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private  List <Product> products  = new ArrayList<>();
}
