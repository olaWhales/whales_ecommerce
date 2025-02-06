package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import com.olawhales.whales_ecommerce.data.model.Users;
import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
