package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import com.olawhales.whales_ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create/")
    public ResponseEntity<?> CreateProduct(@RequestBody CreateProductRequest createProductRequest) {
        try {
            return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<?>deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
        try{
            return new ResponseEntity<>(productService.delete(deleteProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/update/")
    public ResponseEntity<?>updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        try{
            return new ResponseEntity<>(productService.update(updateProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll_product/")
    public ResponseEntity<?>getAll(@RequestBody GetAllProductsRequest getAllProductsRequest){
        try{
            return new ResponseEntity<>(productService.getAll(getAllProductsRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findSingle/")
    public ResponseEntity<?>gitSingleProduct(@RequestBody GetSingleSellerRequest getSingleSellerRequest){
        try{
            return new ResponseEntity<>(productService.getSingleProduct(getSingleSellerRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

