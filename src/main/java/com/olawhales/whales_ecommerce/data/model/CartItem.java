package com.olawhales.whales_ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
//@Data
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;
//
//    @OneToMany(mappedBy = "cartItem", cascade = CascadeType.ALL)
//    private List<RegisterProduct> products;

    @ManyToOne  // Each cart item is linked to ONE product
    private Product product;



//    @ManyToOne
//    private RegisterProduct product;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id" , nullable = false)
//    private List<RegisterProduct> product = new ArrayList<>();


//    @ManyToMany
//    @JoinTable(
//
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private  List <RegisterProduct> product  = new ArrayList<>();

    private int quantity;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
