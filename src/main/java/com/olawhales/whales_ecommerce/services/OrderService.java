package com.olawhales.whales_ecommerce.services;

import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.OrderCreateRequest;
import com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest.OrderUpdateRequest;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.OrderUpdateResponse;
import com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse.OrdersResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrdersResponse createOrder(OrderCreateRequest orderCreateRequest );
    OrderUpdateResponse updateOrder(OrderUpdateRequest orderUpdateRequest);
    OrdersResponse deleteOrder(Long orderId);

}
