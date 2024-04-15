package com.carlosborges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosborges.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
