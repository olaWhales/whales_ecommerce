package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CreateCartItemResponse;

public interface CartService {
//    CreateCartItemResponse response(CreateCartItemRequest createCartItemRequest);

//    CreateCartItemResponse addToCart(CreateCartItemRequest createCartItemRequest);

//    CreateCartItemResponse addToCart(CreateCartItemRequest createCartItemRequest);

    CreateCartItemResponse addToCart(CreateCartItemRequest createCartItemRequest, String username);
}
