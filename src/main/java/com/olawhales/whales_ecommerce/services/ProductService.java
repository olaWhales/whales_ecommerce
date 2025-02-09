package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.productRequest.*;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.productResponse.*;

import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest productRequest);
    DeleteProductResponse delete(DeleteProductRequest deleteProduct);

//    DeleteProductResponse delete(CreateProductRequest deleteProduct);

    UpdateProductResponse update (UpdateProductRequest updateProduct);
    List<GetProductResponse> getAll(GetAllProductsRequest getAll);
    GetProductResponse getSingleProduct(GetSingleSellerRequest getSingle);
}
