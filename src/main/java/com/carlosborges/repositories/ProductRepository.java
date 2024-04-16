package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
