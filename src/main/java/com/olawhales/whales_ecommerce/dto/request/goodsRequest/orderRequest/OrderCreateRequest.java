package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCreateRequest {
    private Long buyerId;
    private List<Long> orderItemList ;
    private LocalDateTime localDateTime;
    private Double totalAmount;
}
