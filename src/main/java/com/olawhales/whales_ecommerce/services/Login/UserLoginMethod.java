package com.olawhales.whales_ecommerce.services.Login;

import com.olawhales.whales_ecommerce.SecurityConfig.JwtService;
import com.olawhales.whales_ecommerce.data.model.Users;
import com.olawhales.whales_ecommerce.data.repositories.UserRepository;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class UserLoginMethod implements UserLogin {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder ;
    @Autowired
    private  JwtService jwtService;

//    public UserLoginMethod( UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//    }

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

    @Override
    public String toString() {
        return "UserLoginMethod{" +
                "userRepository=" + userRepository +
                ", passwordEncoder=" + passwordEncoder +
                ", jwtService=" + jwtService +
                '}';
    }
}
