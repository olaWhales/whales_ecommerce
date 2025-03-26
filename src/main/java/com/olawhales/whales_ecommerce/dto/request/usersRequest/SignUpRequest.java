package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.olawhales.whales_ecommerce.data.model.UserRole;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpRequest {
    private String userName;
    private String email ;
    private String password ;
    private String contact ;
//    private LocalDate dateCreated ;
    private UserRole userRole ;

    private String businessAddress ;
    private String companyName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
