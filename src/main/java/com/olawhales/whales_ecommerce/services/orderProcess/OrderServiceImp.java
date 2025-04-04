package com.olawhales.whales_ecommerce.services.orderProcess;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.*;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.ViewCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.CheckoutCartResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.ViewCartItemResponse;
import com.olawhales.whales_ecommerce.services.SignIn.UserRegister;
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
    @Autowired
    private UserRegister userRegister;

    @Override
    public ViewCartItemResponse viewCartItem(ViewCartItemRequest viewCartItemRequest) {
        Optional<Users> user = userRepository.findByUserName(viewCartItemRequest.getUserName());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Username not found");
        }
        Users users = userRepository.findByUserName(user.get().getUserName()).
                orElseThrow(() -> new IllegalArgumentException("Username not found"));
        viewCartItemRequest.setUserName(users.getUserName());

        return null;
    }

    @Override
    public CheckoutCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest) {
        Optional<Users> user = userRepository.findByUserName(checkoutCartRequest.getUserName());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Username not found");
        }
        Cart cart = cartRepository.findByUsers(user.get());
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        Orders orders = new Orders();
        orders.setUsers(user.get());
        orders.setOrderDate(LocalDateTime.now());
        orders.setStatus(Status.PENDING);
        orderRepository.save(orders);

        // i Convert CartItems to OrderItems
        List<OrderItem> orderItems = createOrderItemsFromCart(cart, orders);

        // I Calculate total order price
        double totalPrice = calculateOrderTotal(orderItems);
        orders.setTotalAmount(totalPrice);

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

    //        Helper method to convert cart items into order items
    private List<OrderItem> createOrderItemsFromCart(Cart cart, Orders orders) {

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItem()) {
            Product product = cartItem.getProduct();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setOrderTime(LocalDateTime.now());
            orderItem.setDeliveryDate(LocalDate.now().plusDays(5));
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getQuantity() * product.getProductPrice()); // Calculate individual item price
            orderItems.add(orderItem);
        }
//            String subject = "`Welcome to @whalesCommerce platform";
//            String body =   "Hello " + user.getUserName() + ", \n\n Thank you for signing up at @whalesCommerce shopping application`";
//            emailService.sendEmail(user.getEmail(), subject, body);
        return orderItems;
    }


    private double calculateOrderTotal(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getProductPrice())
                .sum();

    }
}

