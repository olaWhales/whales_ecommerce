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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key in "Order" table
    private Users users;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany (mappedBy = "orders", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
