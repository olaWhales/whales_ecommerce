package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.CartRepository;
import com.olawhales.whales_ecommerce.data.repositories.OrderItemRepository;
import com.olawhales.whales_ecommerce.data.repositories.OrderRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CheckoutCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CheckoutCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest) {
        Optional<Users> user = userRepository.findByUserName(checkoutCartRequest.getUserName());
        if(user.isEmpty()){
            throw new IllegalArgumentException("Username not found");
        }
        Cart cart = cartRepository.findByUsers(user.get());
        if(cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        Orders orders = new Orders();
        orders.setUsers(user.get());
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderId(orders.getOrderId());
        orders.setStatus(Status.PENDING);
        orderRepository.save(orders);
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItem()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDeliveryDate(LocalDate.now().plusDays(5));
            orderItems.add(orderItem);
        }
        
        System.out.println("This is order Item " +orderItems);
        orderItemRepository.saveAll(orderItems);
        CheckoutCartResponse response = new CheckoutCartResponse();
        response.setMessage("Order created successfully! Thank you for shopping");
        return null;
    }

}
