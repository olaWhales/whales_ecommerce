package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.services.CartService;
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

//    @ResponseBody
//    @PostMapping("/addToCart/")
//    public CreateCartItemResponse addToCart(@RequestBody CreateCartItemRequest createCartItemRequest,
//                                      @AuthenticationPrincipal UserDetails userDetails) {
//        String username = userDetails.getUsername();
//        return cartService.addToCart(createCartItemRequest, username);
//    }


}

