package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
