package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.*;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.CheckoutCartResponse;
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
    @Autowired
    private CartItemRepository cartItemRepository;

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
//        Orders orders = new Orders();
//        orders.setUsers(user.get());
//        orders.setOrderDate(LocalDateTime.now());
//        orders.setOrderId(orders.getOrderId());
//        orders.setStatus(Status.PENDING);
//        orderRepository.save(orders);
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for (CartItem cartItem : cart.getCartItem()) {
//            Product product = cartItem.getProduct();
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrders(orders);
//            orderItem.setDeliveryDate(LocalDate.now().plusDays(5));
//            orderItem.setProduct(cartItem.getProduct());
//            orderItem.setQuantity(cartItem.getQuantity());
////            orderItem.setPrice(cartItem.getQuantity() * product.getProductPrice());
//            orderItem.setPrice(calculateOrderTotal(orders));
//            orderItems.add(orderItem);
//
//
//        }
//
//        System.out.println("This is order Item " +orderItems);
//        orderItemRepository.saveAll(orderItems);
//        CheckoutCartResponse response = new CheckoutCartResponse();
////        response.setTotalPrice(orderItem);
//        response.setMessage("Order created successfully! Thank you for shopping");
//        return null;
//    }
//
//
//    public double calculateOrderTotal(Orders order) {
//        return order.getOrderItems().stream()
//                .mapToDouble(item -> item.getQuantity() * item.getProduct().getProductPrice())
//                .sum();

            // Create new Order
            Orders orders = new Orders();
            orders.setUsers(user.get());
            orders.setOrderDate(LocalDateTime.now());
            orders.setStatus(Status.PENDING);
            orderRepository.save(orders);

            // Convert CartItems to OrderItems
            List<OrderItem> orderItems = createOrderItemsFromCart(cart, orders);

            // Calculate total order price
            double totalPrice = calculateOrderTotal(orderItems);
            orders.setTotalAmount(totalPrice);

            // Save order and order items
            orderRepository.save(orders);
            orderItemRepository.saveAll(orderItems);

            // Clear cart after checkout
            cartItemRepository.deleteAll(cart.getCartItem());

            // Build Response
            CheckoutCartResponse response = new CheckoutCartResponse();
            response.setMessage("Order created successfully! Thank you for shopping");
            response.setTotalPrice(totalPrice);
            return response;
        }

        /**
         * Helper method to convert cart items into order items
         */
        private List<OrderItem> createOrderItemsFromCart(Cart cart, Orders orders) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cartItem : cart.getCartItem()) {
                Product product = cartItem.getProduct();
                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(orders);
                orderItem.setDeliveryDate(LocalDate.now().plusDays(5));
                orderItem.setProduct(product);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getQuantity() * product.getProductPrice()); // Calculate individual item price
                orderItems.add(orderItem);
            }
            return orderItems;
        }

        /**
         * Helper method to calculate total price of all order items
         */
        private double calculateOrderTotal(List<OrderItem> orderItems) {
            return orderItems.stream()
                    .mapToDouble(item -> item.getQuantity() * item.getProduct().getProductPrice())
                    .sum();
        }
    }

