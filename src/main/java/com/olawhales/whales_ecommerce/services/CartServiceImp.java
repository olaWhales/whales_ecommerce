package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.CartItemRepository;
import com.olawhales.whales_ecommerce.data.repositories.CartRepository;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.ClearCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.RemoveCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.ClearCartItemResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CreateCartItemResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.RemoveCartItemResponse;
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
        Long productId = createCartItemRequest.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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

        // Create Response
        CreateCartItemResponse createCartItemResponse = new CreateCartItemResponse();
        createCartItemResponse.setProductId(cartItem.getProduct().getId());
        createCartItemResponse.setQuantity(cartItem.getQuantity());
//        createCartItemResponse.setPrice(product.getProductPrice() * cartItem.getQuantity());
        createCartItemResponse.setPrice(calculateCartTotal(cart));
        return createCartItemResponse;
    }

    @Override
    public RemoveCartItemResponse removeFromCart(RemoveCartItemRequest removeCartItemRequest) {
        Long product = removeCartItemRequest.getCartId();
        CartItem cartItem = cartItemRepository.findById(product).
                orElseThrow(()-> new RuntimeException("Product not found"));
        cartItemRepository.delete(cartItem);
        RemoveCartItemResponse removeCartItemResponse = new RemoveCartItemResponse();
        removeCartItemResponse.setMessage("product remove successfully");
        return removeCartItemResponse;
    }

    @Override
    public ClearCartItemResponse clearCart(ClearCartItemRequest clearCartItemRequest) {
        String username = clearCartItemRequest.getUserName();
        Users user = userRepository.findByUserName(username).
                orElseThrow(()-> new IllegalArgumentException("User not found"));
        Cart cartName = cartRepository.findByUsers(user);
        System.out.println("This is cartName" + cartName);
        if(cartName == null) {
            System.out.println("this is cart after the loop " + cartName);
            throw new IllegalArgumentException("UserName must be found! ");
        }
        cartItemRepository.deleteAll(cartName.getCartItem());

        ClearCartItemResponse response = new ClearCartItemResponse();
        response.setMessage("Cart deleted successfully");
        return response;
    }

    public double calculateCartTotal(Cart cart) {
        return cart.getCartItem().stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getProductPrice())
                .sum();
    }

}





