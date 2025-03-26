package com.olawhales.whales_ecommerce.services.productServices.ViewProductPackage;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.GetAllProductsRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.GetSingleSellerRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.GetProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewProduct {
    List<GetProductResponse> getAll(GetAllProductsRequest getAll);
    GetProductResponse getSingleProduct(GetSingleSellerRequest getSingle);
}
