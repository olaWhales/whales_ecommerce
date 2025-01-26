package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderItemRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderItemCreateRequest {
    private Long buyerId ;
    private Long productId;
    private String city;
    private String houseNumber;
    private String street;
    private LocalDate deliveryDate;
    private Integer quantity;
}
