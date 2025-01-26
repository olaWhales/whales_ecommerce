package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
