package com.olawhales.whales_ecommerce.services.orderProcess;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.CheckoutCartRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.ViewCartItemRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.CheckoutCartResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.ViewCartItemResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ViewCartItemResponse viewCartItem(ViewCartItemRequest viewCartItemRequest);
    CheckoutCartResponse checkoutCart(CheckoutCartRequest checkoutCartRequest);

}
