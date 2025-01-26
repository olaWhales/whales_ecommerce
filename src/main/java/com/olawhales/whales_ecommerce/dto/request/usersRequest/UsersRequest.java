package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import com.olawhales.whales_ecommerce.data.model.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersRequest {
    private String firstName;
    private String lastName;
    private String email ;
    private String password ;
    private String contact ;
//    private LocalDate dateCreated ;
    private UserRole userRole ;

//    public LocalDate getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(LocalDate dateCreated) {
//        this.dateCreated = dateCreated;
//    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
