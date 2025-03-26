package com.olawhales.whales_ecommerce.services.productServices.DeleteProductPackage;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.UserPrincipal;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.DeleteProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.DeleteProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//@Setter
//@Getter
public class DeleteProductMethod implements DeleteProduct{
    private ProductRepository productRepository;

    @Override
    public DeleteProductResponse delete(DeleteProductRequest deleteProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("Delete product method, principal " + principal);
        Users user = principal.users();

        Long productId = deleteProduct.getProductId();
        if (productId == null) {
            throw new IllegalArgumentException("RegisterProduct Id is null");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("RegisterProduct not found"));
        System.out.println("this is the product id " + product.getId());

//        if (!product.getSeller().getId().equals(user.getId())) {
        if (!product.getSeller().getId().equals(user.getSeller().getId())) {
            System.out.println("This is the seller product id  " + product.getSeller().getId());
            System.out.println("This is the seller ID of authenticated user: " + user.getSeller());
            throw new IllegalArgumentException("You can only delete your own products");
        }

        product.setDeleted(true);
        productRepository.save(product); // I saved update instead of deleting

        //  Return success response
        DeleteProductResponse response = new DeleteProductResponse();
        response.setMessage("RegisterProduct deleted successfully");
        return response;
    }

    @Override
    public String toString() {
        return "DeleteProductMethod{" +
                "productRepository=" + productRepository +
                '}';
    }
}
