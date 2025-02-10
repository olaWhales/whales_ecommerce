package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart/")
    public ResponseEntity<?>add(@RequestBody CreateCartItemRequest createCartItemRequest) {
        try{
            return new ResponseEntity<>(cartService.addToCart(createCartItemRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
