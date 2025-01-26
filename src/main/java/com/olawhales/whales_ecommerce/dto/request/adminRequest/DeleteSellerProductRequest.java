package com.olawhales.whales_ecommerce.dto.request.adminRequest;

import lombok.Data;

@Data
public class DeleteSellerProductRequest {
    private Long sellerId;
    private Long productId;
}
