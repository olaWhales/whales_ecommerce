package com.olawhales.whales_ecommerce.dto.response.adminResponse;

import com.olawhales.whales_ecommerce.data.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ViewAllProductsResponse {
    private List<Product> products;
}
