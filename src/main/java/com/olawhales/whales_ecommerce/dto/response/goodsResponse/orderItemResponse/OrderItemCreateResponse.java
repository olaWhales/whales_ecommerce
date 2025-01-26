package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderItemResponse;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderItemCreateResponse {
    private Long orderItemId ;
    private LocalDateTime orderDate ;
    private String city;
    private String street;
    private String houseNumber;
    private LocalDate deliveryDate ;
    private String message ;

}
