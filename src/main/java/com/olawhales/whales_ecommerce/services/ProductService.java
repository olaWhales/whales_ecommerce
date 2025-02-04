package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.CreateProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.DeleteProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.GetAllProductResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.UpdateProductResponse;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest productRequest);
    DeleteProductResponse delete(DeleteProductRequest deleteProduct);

    DeleteProductResponse delete(CreateProductRequest deleteProduct);

    UpdateProductResponse update (UpdateProductRequest updateProduct);
    GetAllProductResponse getAll(GetAllProductsRequest getAll);
    GetAllProductResponse getSingle(GetSingleSellerRequest getSingle);
}
