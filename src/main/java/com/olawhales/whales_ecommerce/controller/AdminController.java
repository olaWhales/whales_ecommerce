package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.adminRequest.AdminRegRequest;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.ViewAllProductRequest;
import com.olawhales.whales_ecommerce.services.AdminService;
import com.olawhales.whales_ecommerce.services.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminServiceImp adminService;

    @PostMapping("/registration/")
    public ResponseEntity <?> registration(@RequestBody AdminRegRequest adminRegRequest){
        try{
            return new ResponseEntity<>(adminService.adminRegister(adminRegRequest) , HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view_single_seller_products")
    public ResponseEntity<?> viewSellerProduct(@RequestBody ViewAllProductRequest viewAllProductRequest) {
        try{
            return new ResponseEntity<>(adminService.viewAllProduct(viewAllProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
