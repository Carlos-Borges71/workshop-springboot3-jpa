package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
