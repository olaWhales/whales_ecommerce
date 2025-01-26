package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.usersRequest.UsersRequest;
import com.olawhales.whales_ecommerce.dto.response.usersResponse.UsersResponse;

public interface UserService {
    UsersResponse register(UsersRequest usersRequest);
}
