package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*")
public class RegistrationController{

    @Autowired
    private UserService userService;

    @PostMapping("/user/")
    public ResponseEntity<?> registerUser(@RequestBody @Validated SignUpRequest registerRequest) {
        try{
            return new ResponseEntity<>(userService.register(registerRequest) , HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try{
            System.out.println("/n/n/n/Christopher Here!/n/n/n");
            return new ResponseEntity<>(userService.login(loginRequest) , HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/test/")
    public String test() {

            return "Raise me up";

    }

}
