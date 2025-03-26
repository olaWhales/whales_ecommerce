package com.olawhales.whales_ecommerce.services.productServices.RegisterProduct;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.CreateProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface RegisterProduct {
    CreateProductResponse createProduct(CreateProductRequest productRequest);

}
