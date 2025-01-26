package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest;

import lombok.Data;

@Data
public class OrderUpdateRequest {
    private Long buyerId ;
    private Long orderId ;
    private Long orderItemId ;
    private Integer quantity ;
}
