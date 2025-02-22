package com.olawhales.whales_ecommerce.dto.response.usersResponse;

import lombok.Data;

@Data
public class UsersResponse {
    private String token ;
    private String userRole;
    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
