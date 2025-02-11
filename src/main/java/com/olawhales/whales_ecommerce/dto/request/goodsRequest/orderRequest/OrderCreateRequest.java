package com.olawhales.whales_ecommerce.dto.request.goodsRequest.orderRequest;

import com.olawhales.whales_ecommerce.data.model.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCreateRequest {
    private List<Long> orderItemList ;
    private LocalDateTime localDateTime;
    private Double totalAmount;
    private Status status;
}
