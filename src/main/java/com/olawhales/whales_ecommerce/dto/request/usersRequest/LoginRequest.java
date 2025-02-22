package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import com.olawhales.whales_ecommerce.data.model.Users;
import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
