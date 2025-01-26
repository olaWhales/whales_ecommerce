package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
