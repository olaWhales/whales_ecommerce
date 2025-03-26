package com.olawhales.whales_ecommerce.services.productServices.UpdateProductPackage;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.UpdateProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.UpdateProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdateProduct {
    UpdateProductResponse update (UpdateProductRequest updateProduct);

}
