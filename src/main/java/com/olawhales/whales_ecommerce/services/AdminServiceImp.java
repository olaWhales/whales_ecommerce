package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.*;
import com.olawhales.whales_ecommerce.data.repositories.AdminRepository;
import com.olawhales.whales_ecommerce.data.repositories.ProductRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.AdminRegRequest;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.DeleteSellerProductRequest;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.ViewAllProductRequest;
import com.olawhales.whales_ecommerce.dto.response.adminResponse.AdminRegResponse;
import com.olawhales.whales_ecommerce.dto.response.adminResponse.DeleteSellerProductResponse;
import com.olawhales.whales_ecommerce.dto.response.adminResponse.ViewAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public AdminRegResponse adminRegister(AdminRegRequest adminRegRequest) {
        Users user = new Users();
        user.setUserName(adminRegRequest.getUsername());
        user.setEmail(adminRegRequest.getEmail());
        user.setPassword(passwordEncoder.encode(adminRegRequest.getPassword())); // Encrypt the password
        user.setContact(adminRegRequest.getContact());
        user.setUserRole(UserRole.ADMIN);
        user.setDateCreated(LocalDateTime.now());

        Users users = userRepository.save(user);

        // Step 2: Link User to Admin
        Admin admin = new Admin();
        admin.setRole("ADMIN");
        admin.setUser(users);
        adminRepository.save(admin);

        AdminRegResponse response = new AdminRegResponse();
        response.setMessage("registration successful");
        return response;
    }

    @Override
        public List<ViewAllProductsResponse> viewAllProduct(ViewAllProductRequest viewAllProductRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("You are not authenticated");
        }

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        if (principal == null) {
            throw new SecurityException("Invalid administrative details");
        }

        String username = principal.getUsername();
        if (username == null) {
            throw new SecurityException("Username not found");
        }

        // Get Seller's ID from Request
        Long sellerId = viewAllProductRequest.getSellerId();
        if (sellerId == null) {
            throw new SecurityException("Seller id must not be null");
        }

        // Verify Seller Ownership
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        System.out.println("this is seller found " + sellerOptional);
        if (sellerOptional.isEmpty()) {
            throw new IllegalArgumentException("Seller not found");
        }

        Seller seller = sellerOptional.get();
        // Check if the authenticated user is ADMIN
        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        // Only check username if the user is NOT an ADMIN
        if (!isAdmin) {
            if (!seller.getUser().getUserName().trim().equalsIgnoreCase(username.trim())) {
                throw new SecurityException("You are not authorized to view this seller's products");
            }
        }

        // Fetch Products by Seller
        List<Product> products = productRepository.findBySeller(seller);

        // Map Products to Response
        List<ViewAllProductsResponse> productResponses = products.stream()
                .map(this::getProductResponse)
                .toList();
        return productResponses;
    }

    @Override
    public DeleteSellerProductResponse deleteSellerProduct(DeleteSellerProductRequest deleteSellerProductRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Invalid administrative details");
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        if (principal == null) {
            throw new SecurityException("Invalid administrative details");
        }

        String username = principal.getUsername();
        if (username == null) {
            throw new SecurityException("Username not found");
        }

        // Get Seller's ID from Request
        Long sellerId = deleteSellerProductRequest.getSellerId();
        if (sellerId == null) {
            throw new SecurityException("Seller id must not be null");
        }

        // Verify Seller Ownership
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        if (sellerOptional.isEmpty()) {
            throw new IllegalArgumentException("Seller not found");
        }

        Seller seller = sellerOptional.get();
        // Check if the authenticated user is ADMIN
        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
        if (!isAdmin) {
            if (!seller.getUser().getUserName().trim().equalsIgnoreCase(username.trim())) {
                throw new SecurityException("You are not authorized to view this seller's products");
            }
        }
//        Product product = productRepository.findBySeller()
//        if (seller.equals()) {
//        }
     return null   ;
    }

    private ViewAllProductsResponse getProductResponse(Product product) {
        ViewAllProductsResponse productResponse = new ViewAllProductsResponse();
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setProductQuantity(product.getProductQuantity());
        return productResponse;
    }
}
