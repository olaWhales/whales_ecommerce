package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        Long sellerId = productRequest.getSellerId();

    Product product = new Product();
        product.setSeller(user.getSeller());
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
    response.setProductId(product.getId());
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
        Users user = principal.getUsers(); // Get authenticated user (seller)

        Long productId = deleteProduct.getProductId();
        if (productId == null) {
            throw new IllegalArgumentException("Product Id is null");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        System.out.println("this is the product id " + product.getId());

//        if (!product.getSeller().getId().equals(user.getId())) {
            if (!product.getSeller().getId().equals(user.getSeller().getId())) {
                System.out.println("This is the seller product id  " + product.getSeller().getId());
                System.out.println("This is the seller ID of authenticated user: " + user.getSeller());
                throw new IllegalArgumentException("You can only delete your own products");
        }

        product.setDeleted(true);
        productRepository.save(product); //  Save update instead of deleting

        //  Return success response
        DeleteProductResponse response = new DeleteProductResponse();
        response.setMessage("Product deleted successfully");
        return response;
}

    @Override
    public UpdateProductResponse update(UpdateProductRequest updateProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.getUsers(); // Get authenticated user (seller// )

        Long productId = updateProduct.getId();
        if (productId == null) {
            throw new IllegalArgumentException("Product Id is null");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        System.out.println("this is the product id " + product.getId());

        if (!product.getSeller().getId().equals(user.getSeller().getId())) {
            System.out.println("This is the seller product id  " + product.getSeller().getId());
            System.out.println("This is the seller ID of authenticated user: " + user.getSeller());
            throw new IllegalArgumentException("You can only update your own products");
        }

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

    @Override
    public List<GetProductResponse> getAll(GetAllProductsRequest getAll) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Users user = principal.getUsers(); // Get authenticated user (seller// )
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
        Users user = principal.getUsers();

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
        productResponse.setMessage("Product found");
        return productResponse;
    }
}
