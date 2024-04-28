package com.carlosborges.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.carlosborges.entities.Category;
import com.carlosborges.repositories.CategoryRepository;
import com.carlosborges.services.exceptions.DatabaseException;
import com.carlosborges.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id){
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Category insert(Category obj) {
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
	public Category update(Long id, Category obj) {
		try {
		Category entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}catch(TransactionSystemException e) {
			throw new DatabaseException(id);
		}
		
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());	
	}
}
