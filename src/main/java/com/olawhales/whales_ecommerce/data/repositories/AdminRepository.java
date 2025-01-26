package com.olawhales.whales_ecommerce.data.repositories;

import com.olawhales.whales_ecommerce.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Long> {
}
