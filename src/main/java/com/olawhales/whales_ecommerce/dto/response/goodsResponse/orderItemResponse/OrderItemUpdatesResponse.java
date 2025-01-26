package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse;

import lombok.Data;

@Data
public class OrderItemUpdatesResponse {
    private Long orderItemId;
    private Integer quantity;
    private String message ;
}
