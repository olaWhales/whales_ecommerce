package com.olawhales.whales_ecommerce.services.productServices.RegisterProduct;

import com.olawhales.whales_ecommerce.SecurityConfig.IdGenerator;
import com.olawhales.whales_ecommerce.cloudinary.CloudinaryService;
import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.UserPrincipal;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.CreateProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class RegisterProductMethod implements RegisterProduct {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IdGenerator idGenerator ;
    @Autowired
    private CloudinaryService cloudinaryService ;

//    public RegisterProductMethod(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public CreateProductResponse createProduct(CreateProductRequest productRequest) {
//        return getCreateProductResponse(productRequest, productRepository);
//    }

    @NotNull
//    public static CreateProductResponse getCreateProductResponse(CreateProductRequest productRequest, ProductRepository productRepository) {
    public CreateProductResponse createProduct(CreateProductRequest productRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            System.out.println( " is not a user " + principal);
            throw new IllegalArgumentException("Invalid user authentication");
        }

        Users user = ((UserPrincipal) principal).users(); // Get actual user
        if (user.getUserRole() != UserRole.SELLER) {
            throw new IllegalArgumentException("You are not permitted to perform this action");
        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductQuantity(productRequest.getProductQuantity());
        product.setProductPrice(productRequest.getProductPrice());
        product.setImages(cloudinaryService.upload(productRequest.getImages()));
        product.setIdGenerator(String.format(idGenerator.generateId()));
        productRepository.save(product);

        CreateProductResponse response = new CreateProductResponse();
        response.setProductName(product.getProductName());
        response.setProductDescription(product.getProductDescription());
        response.setProductQuantity(product.getProductQuantity());
        response.setProductPrice(product.getProductPrice());
        response.setSellerId(user.getSeller().getId());
        response.setProductId(product.getId());
        response.setIdGenerator(String.format(idGenerator.toString()));
        System.out.println("Response: " + response);
        return response;
    }

    @Override
    public String toString() {
        return "RegisterProductMethod{" +
                "productRepository=" + productRepository +
                '}';
    }
}
