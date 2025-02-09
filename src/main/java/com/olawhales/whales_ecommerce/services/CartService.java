package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CartItemResponse;

public interface CartService {
//    CartItemResponse response(CreateCartItemRequest createCartItemRequest);

    CartItemResponse addToCart(CreateCartItemRequest createCartItemRequest);
}
