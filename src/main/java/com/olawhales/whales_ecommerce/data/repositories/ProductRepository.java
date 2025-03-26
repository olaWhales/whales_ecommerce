package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Cart;
import com.olawhales.whales_ecommerce.data.model.CartItem;
import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.Seller;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySellerId(Long user);

    List<Product> findBySeller(Seller seller);

    @NotNull Optional<Product> findById(@NotNull Long productId);


        // Find all cart items for a specific cart
//        List<CartItem> findByCart(Cart cart);
//
//        // Find cart items for a specific product
//        List<CartItem> findByProduct(RegisterProduct product);
//
//        // Find a specific cart item by cart and product (useful for checking if an item exists in a cart)
//        Optional<CartItem> findByCartAndProduct(Cart cart, RegisterProduct product);
//    }

}
