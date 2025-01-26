package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest;

import lombok.Data;

@Data
public class OrderItemCancelRequest {
    private Long buyerId;
    private Long orderItemId;
}
