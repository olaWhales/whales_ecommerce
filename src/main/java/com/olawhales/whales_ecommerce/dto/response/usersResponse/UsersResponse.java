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
}
