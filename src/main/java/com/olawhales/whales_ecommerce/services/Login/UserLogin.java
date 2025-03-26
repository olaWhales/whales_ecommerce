package com.olawhales.whales_ecommerce.services.Login;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.LoginRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;

public interface UserLogin {
    UsersResponse login(LoginRequest loginRequest);
}
