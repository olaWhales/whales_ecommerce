package com.olawhales.whales_ecommerce.dto.response.goodsResponse.orderResponse;

import com.olawhales.whales_ecommerce.data.model.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class ViewCartItemResponse {
    private List<CartItem> cartItemList;
}
