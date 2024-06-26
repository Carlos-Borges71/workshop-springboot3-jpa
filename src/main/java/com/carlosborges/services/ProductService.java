package com.carlosborges.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.carlosborges.entities.Product;
import com.carlosborges.repositories.ProductRepository;
import com.carlosborges.services.exceptions.DatabaseException;
import com.carlosborges.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}	
	
	public Product findById(Long id){
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product obj) {
		try {
		return repository.save(obj);
		}catch(ConstraintViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	 
	public void delete(Long id) {
		Boolean del = repository.existsById(id);
		if(del == false) {
			 throw new ResourceNotFoundException(id);
		} 
		try {
			repository.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(id);
		}
		
	}
	public Product update(Long id, Product obj) {
		try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}catch(TransactionSystemException e) {
			throw new DatabaseException(id);
		}
		
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
	}
}
