package com.olawhales.whales_ecommerce.services.orderProcess;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.ClearCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.CreateCartItemRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.carts.RemoveCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.ClearCartItemResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.CreateCartItemResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.cartResponse.RemoveCartItemResponse;

public interface CartService {
    CreateCartItemResponse addToCart(CreateCartItemRequest createCartItemRequest, String username);
    RemoveCartItemResponse removeFromCart(RemoveCartItemRequest removeCartItemRequest);
    ClearCartItemResponse clearCart(ClearCartItemRequest clearCartItemRequest);

}
