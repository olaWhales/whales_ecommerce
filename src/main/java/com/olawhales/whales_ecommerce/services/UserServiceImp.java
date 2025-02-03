package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.Buyer;
import com.olawhales.whales_ecommerce.data.model.Seller;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.AdminRepository;
import com.olawhales.whales_ecommerce.data.repositories.BuyerRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SellerRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImp implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SellerRepository sellerRepository;

        @Autowired
        private BuyerRepository buyerRepository;

    @Autowired
    private AdminRepository adminRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UsersResponse register(SignUpRequest signUpRequest) {
            Users user = new Users();
            user.setUserName(signUpRequest.getUserName());
            user.setEmail(signUpRequest.getEmail());
        String harshPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(harshPassword); // Hash password
            user.setUserRole(signUpRequest.getUserRole());
            user.setContact(signUpRequest.getContact());
            user.setDateCreated(LocalDateTime.now());
            userRepository.save(user);
            switch (signUpRequest.getUserRole()) {
                case SELLER:
                    Seller seller = new Seller();
                    SellerRequest sellerRequest = new SellerRequest();
                    seller.setCompanyName(sellerRequest.getCompanyName());
                    seller.setBusinessAddress(sellerRequest.getBusinessAddress());
                    seller.setUser(user);
                    sellerRepository.save(seller);
                    break;
                case BUYER:
                    Buyer buyer = new Buyer();
                    buyer.setUser(user);
                    buyerRepository.save(buyer);
                    break;
//                case ADMIN:
//                    Admin admin = new Admin();
//                    admin.setRole("SuperAdmin"); // Example admin role
//                    admin.setUser(user);
//                    adminRepository.save(admin);
//                    break;
            }
            userRepository.save(user);
            UsersResponse usersResponse = new UsersResponse();
            usersResponse.setMessage("REGISTERED SUCCESSFUL");
        return usersResponse;
    }


}
