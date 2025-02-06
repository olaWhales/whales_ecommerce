package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;

public interface UserService {
    UsersResponse register(SignUpRequest signUpRequest);
    UsersResponse login(LoginRequest loginRequest);
}
