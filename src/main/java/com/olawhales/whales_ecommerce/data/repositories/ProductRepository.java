package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Product;
import com.olawhales.whales_ecommerce.data.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySellerId(Long user);
    Optional<Product> findById(Long id);
//    List<Product> findBySellerUsername(String username);

    List<Product> findBySeller(Seller seller);

//    List<Product> findBySellerUsername();
}
