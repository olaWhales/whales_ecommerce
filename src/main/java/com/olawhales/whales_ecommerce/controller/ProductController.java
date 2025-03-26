package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.CreateProductRequest;
import com.olawhales.whales_ecommerce.services.productServices.DeleteProductPackage.DeleteProduct;
//import com.olawhales.whales_ecommerce.services.ProductService;
import com.olawhales.whales_ecommerce.services.productServices.RegisterProduct.RegisterProduct;
import com.olawhales.whales_ecommerce.services.productServices.RegisterProduct.RegisterProductMethod;
import com.olawhales.whales_ecommerce.services.productServices.UpdateProductPackage.UpdateProduct;
import com.olawhales.whales_ecommerce.services.productServices.ViewProductPackage.ViewProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
//@AllArgsConstructor

//@RequiredArgsConstructor
public class ProductController {

//    private final ProductService productService;
    private final RegisterProductMethod registerProduct;
    private final DeleteProduct deleteProduct;
    private final UpdateProduct updateProduct;
    private final ViewProduct viewProduct;

    @Autowired
    public ProductController(RegisterProductMethod registerProduct,
                             DeleteProduct deleteProduct,
                             UpdateProduct updateProduct,
                             ViewProduct viewProduct) {
        this.registerProduct = registerProduct;
        this.deleteProduct = deleteProduct;
        this.updateProduct = updateProduct;
        this.viewProduct = viewProduct;
    }

    @PostMapping(value = "/create/",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreateProduct(@ModelAttribute CreateProductRequest createProductRequest) {
        try {
            return new ResponseEntity<>(registerProduct.createProduct(createProductRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<?>deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
        try{
            return new ResponseEntity<>(deleteProduct.delete(deleteProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/")
    public ResponseEntity<?>updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        try{
            return new ResponseEntity<>(updateProduct.update(updateProductRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findAll_product/")
    public ResponseEntity<?>getAll(@RequestBody GetAllProductsRequest getAllProductsRequest){
        try{
            return new ResponseEntity<>(viewProduct.getAll(getAllProductsRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

@PostMapping("/findSingle/")
public ResponseEntity<?> getSingleProduct(@RequestBody GetSingleSellerRequest getSingleSellerRequest) {
    System.out.println("Received request: RegisterProduct ID = " + getSingleSellerRequest.getProductId() +
            ", Seller ID = " + getSingleSellerRequest.getSellerId());

    try {
        return new ResponseEntity<>(viewProduct.getSingleProduct(getSingleSellerRequest), HttpStatus.OK);
    } catch (Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


}

