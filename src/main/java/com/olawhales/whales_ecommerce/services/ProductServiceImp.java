package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.DeleteProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.GetAllProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.UpdateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public CreateProductResponse createProduct(CreateProductRequest productRequest) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
        throw new IllegalArgumentException("User is not authenticated");
    }

    Object principal = authentication.getPrincipal();
    if (!(principal instanceof Users)) {
        throw new IllegalArgumentException("Invalid user authentication");
    }
    Users user = (Users) principal;
    if (user.getUserRole() != UserRole.SELLER) {
        throw new IllegalArgumentException("You are not permitted to perform this action");
    }
    Product product = new Product();
    product.setProductName(productRequest.getProductName());
    product.setProductDescription(productRequest.getProductDescription());
    product.setProductQuantity(productRequest.getProductQuantity());
    product.setProductPrice(productRequest.getProductPrice());
    productRepository.save(product);

    CreateProductResponse response = new CreateProductResponse();
    response.setProductName(product.getProductName());
    response.setProductDescription(product.getProductDescription());
    response.setProductQuantity(product.getProductQuantity());
    response.setProductPrice(product.getProductPrice());

    return response;
}

    @Override
    public DeleteProductResponse delete(DeleteProductRequest deleteProduct) {
        return null;
    }

    @Override
    public DeleteProductResponse delete(CreateProductRequest deleteProduct) {
        return null;
    }

    @Override
    public UpdateProductResponse update(UpdateProductRequest updateProduct) {
        return null;
    }

    @Override
    public GetAllProductResponse getAll(GetAllProductsRequest getAll) {
        return null;
    }

    @Override
    public GetAllProductResponse getSingle(GetSingleSellerRequest getSingle) {
        return null;
    }
}
