package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest.OrderItemCancelRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest.OrderItemCreateRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest.OrderItemUpdateRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse.OrderItemCancelResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse.OrderItemCreateResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse.OrderItemUpdatesResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    OrderItemCreateResponse createOrderItemToPurchase(OrderItemCreateRequest orderItemCreateRequest);
    OrderItemUpdatesResponse updateOrderItem(OrderItemUpdateRequest orderItemUpdateRequest);
    OrderItemCancelResponse buyerCancelOrderItem(OrderItemCancelRequest orderItemCancelRequest);
}
