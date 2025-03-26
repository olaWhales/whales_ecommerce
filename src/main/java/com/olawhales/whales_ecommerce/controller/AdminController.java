package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.adminRequest.AdminRegRequest;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.ViewAllProductRequest;
import com.olawhales.whales_ecommerce.services.Admin.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminServiceImp adminServiceImp;

    @PostMapping("/registration/")
    public ResponseEntity <?> registration(@RequestBody AdminRegRequest adminRegRequest){
        try{
            return new ResponseEntity<>(adminServiceImp .adminRegister(adminRegRequest) , HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view_single_seller_products")
    public ResponseEntity<?> viewSellerProduct(@RequestBody ViewAllProductRequest viewAllProductRequest) {
        try{
            return new ResponseEntity<>(adminServiceImp.viewAllProduct(viewAllProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
