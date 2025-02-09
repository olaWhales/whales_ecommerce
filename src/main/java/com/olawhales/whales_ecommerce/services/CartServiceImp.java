package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.CartItemRepository;
import com.olawhales.whales_ecommerce.data.repositories.CartRepository;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CartItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public CartItemResponse addToCart(CreateCartItemRequest createCartItemRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            System.out.println("Invalid user: " + principal);
            throw new IllegalArgumentException("Invalid user authentication");
        }
        Users user = ((UserPrincipal) principal).getUsers(); // Get logged-in user

        // ✅ Only buyers can add to cart
        if (user.getUserRole() != UserRole.BUYER) {
            throw new IllegalArgumentException("Only buyers can add to cart");
        }

        // ✅ Get product from database
        Long productId = createCartItemRequest.getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // ✅ Check if the user has a cart, else create one
        Cart cart = cartRepository.findById(user.getId()).
                orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUsers(user);
                    return cartRepository.save(newCart);
                });

        // ✅ Check if product already exists in cart
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));

        if (cartItem != null) {
            // If the product is already in the cart, increase quantity
            cartItem.setQuantity(cartItem.getQuantity() + createCartItemRequest.getQuantity());
        } else {
            // Otherwise, create new cart item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(createCartItemRequest.getQuantity());
            cart.getCartItem().add(cartItem);
        }

        // Save the updated cart
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        // Return success response
        CartItemResponse response = new CartItemResponse();
        response.setMessage("Product added to cart successfully");
        response.setProductId(product.getId());
        response.setQuantity(cartItem.getQuantity());
        return response;
    }
}

