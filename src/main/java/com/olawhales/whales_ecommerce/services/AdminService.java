package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.adminRequest.AdminRegRequest;
import com.olawhales.whales_ecommerce.dto.request.adminRequest.ViewAllProductRequest;
import com.olawhales.whales_ecommerce.dto.response.adminResponse.AdminRegResponse;
import com.olawhales.whales_ecommerce.dto.response.adminResponse.ViewAllProductsResponse;

import java.util.List;

public interface AdminService {
    AdminRegResponse adminRegister(AdminRegRequest adminRegRequest);
    List<ViewAllProductsResponse> viewAllProduct(ViewAllProductRequest viewAllProductRequest);
      ViewAllProductsResponse viewAllProduct();
}
