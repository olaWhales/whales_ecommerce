package com.olawhales.whales_ecommerce.services.SignIn;

import com.olawhales.whales_ecommerce.data.model.Buyer;
import com.olawhales.whales_ecommerce.data.model.Seller;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.BuyerRepository;
import com.olawhales.whales_ecommerce.data.repositories.SellerRepository;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UserReg;
import com.olawhales.whales_ecommerce.emailSpringEventPackage.EmailService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class UserRegisterMethod implements UserRegister {

    private final UserRepository userRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserRegisterMethod(UserRepository userRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService ) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.emailService = emailService;
     }

//    @Override
    public UserReg register(SignUpRequest signUpRequest) {
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
        if (signUpRequest.getPassword() == null || signUpRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
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
    public String toString() {
        return "UserRegisterMethod{" +
                "userRepository=" + userRepository +
                ", buyerRepository=" + buyerRepository +
                ", sellerRepository=" + sellerRepository +
                ", passwordEncoder=" + passwordEncoder +
                ", emailService=" + emailService +
                '}';
    }
}
