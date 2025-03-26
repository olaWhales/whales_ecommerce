package com.olawhales.whales_ecommerce.services.SignIn;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.SignUpRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UserReg;

public interface UserRegister {
    UserReg register(SignUpRequest signUpRequest);
}
