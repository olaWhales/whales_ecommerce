package com.olawhales.whales_ecommerce.dto.request.adminRequest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
//@Data
public class AdminRegRequest {
    private String username;
    private String password;
    private String email;
    private String contact ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
