package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.CartItemRepository;
import com.olawhales.whales_ecommerce.data.repositories.CartRepository;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartItemResponse addToCart(CreateCartItemRequest createCartItemRequest) {
        return null;
    }

    @Override
    public CartItemResponse addToCart(CreateCartItemRequest createCartItemRequest, String username) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(()-> new IllegalArgumentException("User not found"));
        Long productId = createCartItemRequest.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Find or Create Cart
        Cart cart = cartRepository.findByUsers(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUsers(user);
            cartRepository.save(cart); // Save new cart
        }

        // Add item to cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(createCartItemRequest.getQuantity());
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);

        // Create Response
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setProductId(cartItem.getProduct().getId());
        cartItemResponse.setQuantity(cartItem.getQuantity());

        return cartItemResponse;
    }

}

//    public CartItemResponse addToCart(CreateCartItemRequest createCartItemRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new IllegalArgumentException("User is not authenticated");
//        }
//
//        Object principal = authentication.getPrincipal();
//        if (!(principal instanceof UserPrincipal)) {
//            System.out.println("Invalid user: " + principal);
//            throw new IllegalArgumentException("Invalid user authentication");
//        }
//        Users user = ((UserPrincipal) principal).getUsers(); // Get logged-in user
//
//        // Only buyers can add to cart
//        if (user.getUserRole() != UserRole.BUYER) {
//            throw new IllegalArgumentException("Only buyers can add to cart");
//        }
//
//        //  Get product from database
//        Long productId = createCartItemRequest.getProductId();
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//
//        // Check if the user has a cart, else create one
//        Cart cart = cartRepository.findById(user.getId()).
//                orElseGet(() -> {
//                    Cart newCart = new Cart();
//                    newCart.setUsers(user);
//                    return cartRepository.save(newCart);
//                });
//
//    // Check if product already exists in cart
//        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
//                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
//
//        if (cartItem != null) {
//            // If the product is already in the cart, increase quantity
//            cartItem.setQuantity(cartItem.getQuantity() + createCartItemRequest.getQuantity());
//        } else {
//            // Otherwise, create new cart item
//            cartItem = new CartItem();
//            cartItem.setCart(cart);
//            cartItem.setProduct(product);
//            cartItem.setQuantity(createCartItemRequest.getQuantity());
//            cart.getCartItem().add(cartItem);
//        }
//
//        // Save the updated cart
//        cartRepository.save(cart);
//        cartItemRepository.save(cartItem);
//
//        // Return success response
//        CartItemResponse response = new CartItemResponse();
//        response.setMessage("Product added to cart successfully");
//        response.setProductId(product.getId());
//        response.setQuantity(cartItem.getQuantity());
//        return response;
//    }




