package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.DeleteProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.GetAllProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.UpdateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;

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

        Users user = ((UserPrincipal) principal).getUsers(); // Get actual user
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
    response.setSellerId(user.getSeller().getId());
        System.out.println("Response: " + response);
    return response;
}

    @Override
    public DeleteProductResponse delete(DeleteProductRequest deleteProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.getUsers();

        Long productId = deleteProduct.getProductId();
        if(productId == null){
            throw new IllegalArgumentException("Product Id is null");
        }

//        Long sellerId = deleteProduct.getSellerId();
//        if(sellerId == null){
//            throw new IllegalArgumentException("Seller Id is null");
//        }

//        Seller seller = sellerRepository.findById(sellerId).
//                orElseThrow(()-> new IllegalArgumentException("Seller Id must not be null"));
//         Get product from DB
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (!product.getSeller().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You can only delete your own products");
        }

        // Soft delete (instead of hard delete)
//        product.setDeleted(true);
//        product.setSeller(seller);

        productRepository.deleteById(productId);
        DeleteProductResponse response = new DeleteProductResponse();
        response.setMessage("Product deleted successful");
        return response;
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
