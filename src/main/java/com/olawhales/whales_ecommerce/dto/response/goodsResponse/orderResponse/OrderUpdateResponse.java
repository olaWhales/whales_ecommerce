package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse;

import lombok.Data;

@Data
public class OrderUpdateResponse {
    private Long orderId;
    private Double totalAmount;
    private String message ;
}
