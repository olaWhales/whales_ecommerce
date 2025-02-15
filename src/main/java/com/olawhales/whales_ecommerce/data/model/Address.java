package com.olawhales.whales_ecommerce.data.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String street ;
    private String city ;
    private String houseNumber ;

}

