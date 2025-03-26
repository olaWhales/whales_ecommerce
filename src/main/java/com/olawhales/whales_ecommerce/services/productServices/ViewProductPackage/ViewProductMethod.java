package com.olawhales.whales_ecommerce.services.productServices.ViewProductPackage;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.UserPrincipal;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.GetAllProductsRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.GetSingleSellerRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.GetProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ViewProductMethod implements ViewProduct{
    private ProductRepository productRepository;

    @Override
    public List<GetProductResponse> getAll(GetAllProductsRequest getAll) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.users(); // Get authenticated user (seller// )
        Long seller = getAll.getSellerId();

        List<Product> products = productRepository.findAllBySellerId(seller) ;
        if(products.isEmpty()) {
            throw new IllegalArgumentException("No productsController found");
        }
        if (!user.getSeller().getId().equals(products.get(0).getSeller().getId())) {
            throw new IllegalArgumentException("You can only update your own products");
        }

        return products.stream()
                .map(product -> {
                    GetProductResponse productResponse = new GetProductResponse();
                    productResponse.setProductName(product.getProductName());
                    productResponse.setProductDescription(product.getProductDescription());
                    productResponse.setProductPrice(product.getProductPrice());
                    productResponse.setProductQuantity(product.getProductQuantity());
                    return getProductResponse(productResponse, product);
                })
                .toList();
    }


    @Override
    public GetProductResponse getSingleProduct(GetSingleSellerRequest getSingleProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.users();

        Long productId = getSingleProduct.getProductId();
        Optional<Product> product = productRepository.findById(productId);
        if (!user.getSeller().getId().equals(product.get().getSeller().getId())){
            throw new IllegalArgumentException("You can only find your own products");
        }
        GetProductResponse response = new GetProductResponse();
        Product products = product.get();
        return getProductResponse(response , products);
    }

    private GetProductResponse getProductResponse(GetProductResponse productResponse, Product product) {
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setProductQuantity(product.getProductQuantity());
        productResponse.setMessage("RegisterProduct found");
        return productResponse;
    }
}
