package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {
}
