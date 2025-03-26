package com.olawhales.whales_ecommerce.services.orderProcess;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest.OrderItemCreateRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse.OrderItemCreateResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    OrderItemCreateResponse createOrderItemToPurchase(OrderItemCreateRequest orderItemCreateRequest);
}
