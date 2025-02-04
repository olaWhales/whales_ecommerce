package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.Buyer;
import com.olawhales.whales_ecommerce.data.model.Seller;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.AdminRepository;
import com.olawhales.whales_ecommerce.data.repositories.BuyerRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SellerRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.BuyerResponse;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.SellerResponse;
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


    public UsersResponse register( SignUpRequest signUpRequest) {
        Users user = new Users();
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setUserRole(signUpRequest.getUserRole());
        user.setContact(signUpRequest.getContact());
        user.setDateCreated(LocalDateTime.now());
        userRepository.save(user);

        UsersResponse response = new UsersResponse();
        response.setMessage("REGISTERED SUCCESSFUL");
        if (signUpRequest.getUserRole() == UserRole.SELLER){
            if (signUpRequest.getCompanyName() == null || signUpRequest.getBusinessAddress() == null) {
                throw new IllegalArgumentException("Company name and business address are required for sellers.");
            }
            Seller seller = new Seller();
            seller.setCompanyName(signUpRequest.getCompanyName());
            seller.setBusinessAddress(signUpRequest.getBusinessAddress());
            seller.setUser(user);
            sellerRepository.save(seller);
        } else {
            Buyer buyer = new Buyer();
            buyer.setUser(user);
            buyerRepository.save(buyer);
        }
        return response;
    }

//            switch (signUpRequest.getUserRole()) {
//                case SELLER:
//                    Seller seller = new Seller();
//                    SellerRequest sellerRequest = new SellerRequest();
//                    seller.setCompanyName(signUpRequest.getCompanyName());
//                    seller.setBusinessAddress(signUpRequest.getBusinessAddress());
//                    seller.setUser(user);
//                    sellerRepository.save(seller);
//                    break;
//                case BUYER:
//                    Buyer buyer = new Buyer();
//                    buyer.setUser(user);
//                    buyerRepository.save(buyer);
//                    break;
//            }
//            userRepository.save(user);
//            UsersResponse usersResponse = new UsersResponse();
//            usersResponse.setMessage("REGISTERED SUCCESSFUL");
//        return usersResponse;
//        return response;
//    }
}
