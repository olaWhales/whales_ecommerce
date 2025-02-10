package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.CartItemRepository;
import com.olawhales.whales_ecommerce.data.repositories.CartRepository;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CreateCartItemResponse;
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
    public CreateCartItemResponse addToCart(CreateCartItemRequest createCartItemRequest, String username) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(()-> new IllegalArgumentException("User not found"));
        System.out.println("This is user " + user);
        Long productId = createCartItemRequest.getProductId();
        System.out.println("This is product id" + productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("This is product " + product);
        // Find or Create Cart
        Cart cart = cartRepository.findByUsers(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUsers(user);
            cartRepository.save(cart); // Save new cart
            System.out.println("This is cart " + cart);
        }
        // Add item to cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(createCartItemRequest.getQuantity());
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
        System.out.println("This is all cartItem " + cartItem);

        // Create Response
        CreateCartItemResponse createCartItemResponse = new CreateCartItemResponse();
        createCartItemResponse.setProductId(cartItem.getProduct().getId());
        createCartItemResponse.setQuantity(cartItem.getQuantity());
        System.out.println("This is cart Item " + createCartItemResponse);
        return createCartItemResponse;
    }


}





