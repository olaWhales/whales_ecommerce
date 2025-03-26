package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.ClearCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.RemoveCartItemRequest;
import com.olawhales.whales_ecommerce.services.orderProcess.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart/")
    public ResponseEntity<?>add(@RequestBody CreateCartItemRequest createCartItemRequest ) {
        String username = createCartItemRequest.getUsername();
        try{
            return new ResponseEntity<>(cartService.addToCart(createCartItemRequest , username), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @DeleteMapping("/removeFromCart/")
    public ResponseEntity<?>addToCart(@RequestBody RemoveCartItemRequest removeCartItemRequest) {
        try{
            return new ResponseEntity<>(cartService.removeFromCart(removeCartItemRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
    @ResponseBody
    @DeleteMapping("/deleteCart/")
    public ResponseEntity<?>removeFromCart(@RequestBody ClearCartItemRequest clearCartItemRequest) {
        try{
            return new ResponseEntity<>(cartService.clearCart(clearCartItemRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }


}

