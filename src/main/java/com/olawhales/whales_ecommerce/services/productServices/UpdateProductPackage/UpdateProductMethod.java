package com.olawhales.whales_ecommerce.services.productServices.UpdateProductPackage;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.UserPrincipal;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.UpdateProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProductMethod implements UpdateProduct {
    private ProductRepository productRepository;

    @Override
    public UpdateProductResponse update(UpdateProductRequest updateProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.users();

        Long productId = updateProduct.getId();
        if (productId == null) {
            throw new IllegalArgumentException("RegisterProduct Id is empty");
        }
        Long sellerId = updateProduct.getSellerId();
        if (sellerId == null) {
            throw new IllegalArgumentException("Seller Id is missing in request");
        }
        if(user.getSeller() == null){
            throw new IllegalArgumentException("You are not permitted to perform this action");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("RegisterProduct not found"));
        System.out.println("this is the product id " + product.getId());

        if (!product.getSeller().getId().equals(user.getSeller().getId())) {
            System.out.println("This is the seller product id  " + product.getSeller().getId());
            System.out.println("This is the seller ID of authenticated user: " + user.getSeller());
            throw new IllegalArgumentException("You can only update your own products");
        }
//        product.setId(productId);
//        product.setSeller(user.getSeller());
        product.setProductName(updateProduct.getProductName());
        product.setProductDescription(updateProduct.getProductDescription());
        product.setProductPrice(updateProduct.getProductPrice());
        product.setProductQuantity(updateProduct.getProductQuantity());
        productRepository.save(product);
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        updateProductResponse.setProductName(updateProduct.getProductName());
        updateProductResponse.setProductDescription(updateProduct.getProductDescription());
        updateProductResponse.setProductPrice(updateProduct.getProductPrice());
        updateProductResponse.setProductQuantity(updateProduct.getProductQuantity());
        updateProductResponse.setMessage("product updated successfully");

        return updateProductResponse;
    }
}
