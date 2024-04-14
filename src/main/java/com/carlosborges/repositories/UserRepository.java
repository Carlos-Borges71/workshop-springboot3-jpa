package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
