package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import lombok.Data;

@Data
public class AdminRequest {
    private String firstName;
    private String lastName;
    private String contact ;
    private String email ;
//    private LocalDate birthDate ;
    private String password ;
    private String roles ;
    private String permission ;
}
