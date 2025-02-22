package com.olawhales.whales_ecommerce.dto.response.adminResponse;

import lombok.Data;

@Data
public class AdminRegResponse {
    private String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
