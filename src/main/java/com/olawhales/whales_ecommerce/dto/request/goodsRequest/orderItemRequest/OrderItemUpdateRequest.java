package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest;

import lombok.Data;

@Data
public class OrderItemUpdateRequest {
    private Long orderItemId;
    private Long buyerId;
    private Integer quantity ;
}
