package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.services.Login.UserLogin;
import com.olawhales.whales_ecommerce.services.SignIn.UserRegister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/register")
//@RequiredArgsConstructor
public class RegistrationController{
    private final UserRegister userRegister;
    private final UserLogin userLogin;

    public RegistrationController(UserRegister userRegister, UserLogin userLogin) {
        this.userRegister = userRegister;
        this.userLogin = userLogin;
    }

    @PostMapping("/user/")
    public ResponseEntity<?> registerUser(@RequestBody @Validated SignUpRequest registerRequest) {
        try{
            return new ResponseEntity<>(userRegister.register(registerRequest) , HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            return new ResponseEntity<>(userLogin.login(loginRequest) , HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String toString() {
        return "RegistrationController{" +
                "userRegister=" + userRegister +
                ", userLogin=" + userLogin +
                '}';
    }
}
