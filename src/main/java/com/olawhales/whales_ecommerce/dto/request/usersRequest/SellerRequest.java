package com.olawhales.whales_ecommerce.dto.request.usersRequest;

import lombok.Data;

@Data
public class SellerRequest {
//    @NonNull
//    private String firstName;
//    private String lastName;
//    private String contact ;
//    private String email ;
    private String companyName;
    private String businessAddress;
//    private Address address ;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
}
