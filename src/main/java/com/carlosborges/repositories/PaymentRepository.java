package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
