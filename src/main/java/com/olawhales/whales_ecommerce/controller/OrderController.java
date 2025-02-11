package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/")
//@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/")
    public ResponseEntity<?>CheckoutCart(@RequestBody CheckoutCartRequest checkoutCartRequest) {
        try{
            return new ResponseEntity<>(orderService.checkoutCart(checkoutCartRequest),HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
