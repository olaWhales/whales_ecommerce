package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.data.model.Admin;
import com.olawhales.whales_ecommerce.data.model.Buyer;
import com.olawhales.whales_ecommerce.data.model.Seller;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.AdminRepository;
import com.olawhales.whales_ecommerce.data.repositories.BuyerRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SellerRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.UsersRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UsersResponse register(UsersRequest usersRequest) {
            Users user = new Users();
            user.setFirstName(usersRequest.getFirstName());
            user.setLastName(usersRequest.getLastName());
            user.setEmail(usersRequest.getEmail());
            user.setPassword(usersRequest.getPassword()); // Hash password
            user.setUserRole(usersRequest.getUserRole());
            user.setContact(usersRequest.getContact());
            // Assign role-specific data
            switch (usersRequest.getUserRole()) {
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
                case ADMIN:
                    Admin admin = new Admin();
                    admin.setRole("SuperAdmin"); // Example admin role
                    admin.setUser(user);
                    adminRepository.save(admin);
                    break;
            }
            userRepository.save(user);
            UsersResponse usersResponse = new UsersResponse();
            usersResponse.setMessage("REGISTERED SUCCESSFUL");
        return usersResponse;
    }


}
