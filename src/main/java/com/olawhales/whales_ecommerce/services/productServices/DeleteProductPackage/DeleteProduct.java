package com.olawhales.whales_ecommerce.services.productServices.DeleteProductPackage;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.DeleteProductRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.DeleteProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface DeleteProduct {
    DeleteProductResponse delete(DeleteProductRequest deleteProduct);

}