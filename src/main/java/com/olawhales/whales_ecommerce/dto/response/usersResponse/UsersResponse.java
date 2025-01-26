package com.olawhales.whales_ecommerce.dto.response.usersResponse;

import lombok.Data;

@Data
public class UsersResponse {
    private Long id ;
    private String message ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
