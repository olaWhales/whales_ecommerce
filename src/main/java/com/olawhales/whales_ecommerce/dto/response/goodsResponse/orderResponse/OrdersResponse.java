package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdersResponse {
    private Long orderId ;
    private Double totalAmount ;
    private LocalDateTime localDateTime;
    private String message ;
}
