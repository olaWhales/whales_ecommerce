package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.SecurityConfig.JwtService;
import com.olawhales.whales_ecommerce.data.model.Buyer;
import com.olawhales.whales_ecommerce.data.model.Seller;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.AdminRepository;
import com.olawhales.whales_ecommerce.data.repositories.BuyerRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UserReg;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
import com.olawhales.whales_ecommerce.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SellerRepository sellerRepository;

        @Autowired
        private BuyerRepository buyerRepository;
        @Autowired
        private JwtService jwtService;
        @Autowired
        private EmailService emailService;

    @Autowired
    private AdminRepository adminRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    @Autowired
//    private JavaMailSenderImpl mailSender;


    public UserReg register( SignUpRequest signUpRequest) {
        System.out.println("this is username before registration " + signUpRequest.getUserName());

        UserReg response = new UserReg();
        Optional<Users> existingUser = userRepository.findByEmail(signUpRequest.getEmail());
            if (existingUser.isPresent()) {
                response.setMessage("Email already exists");
                return response;
            }

            Users user = new Users();
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setUserRole(signUpRequest.getUserRole());
        user.setContact(signUpRequest.getContact());
        user.setDateCreated(LocalDateTime.now());
        userRepository.save(user);

        if (signUpRequest.getUserRole() == UserRole.SELLER){
            if (signUpRequest.getCompanyName() == null || signUpRequest.getBusinessAddress() == null) {
                throw new IllegalArgumentException("Company name and business address are required for sellers.");
            }
            Seller seller = new Seller();
            seller.setCompanyName(signUpRequest.getCompanyName());
            seller.setBusinessAddress(signUpRequest.getBusinessAddress());
            seller.setUser(user);
            sellerRepository.save(seller);
        }
        else {
            Buyer buyer = new Buyer();
            buyer.setUser(user);
            buyerRepository.save(buyer);
        }

        String subject = "`Welcome to @whalesCommerce platform";
        String body =   "Hello " + user.getUserName() + ", \n\n Thank you for signing up at @whalesCommerce shopping application`";
        emailService.sendEmail(user.getEmail(), subject, body);

        response.setMessage("REGISTERED SUCCESSFUL");

        System.out .println("Registration email sent to  " + user.getEmail());
        return response;
    }

    @Override
    public UsersResponse login(LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        if ( userName.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password are required.");
        }
        Users user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        // Check if the password matches
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Generate JWT token (if using JWT)
        String token = jwtService.GenerateToken(userName);

        UsersResponse response = new UsersResponse();
        response.setToken(token);  // Add a new field for token in UsersResponse
        response.setUserRole(user.getUserRole().toString()); // Add role to response
        response.setMessage("LOGIN SUCCESSFUL");

        return response;
    }
}
