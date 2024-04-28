package com.carlosborges.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.carlosborges.entities.Payment;
import com.carlosborges.repositories.PaymentRepository;
import com.carlosborges.services.exceptions.DatabaseException;
import com.carlosborges.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	public List<Payment> findAll(){
		return repository.findAll();
	}
	
	public Payment findById(Long id){
		Optional<Payment> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Payment insert(Payment obj) {
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
	public Payment update(Long id, Payment obj) {
		try {
		Payment entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}catch(TransactionSystemException e) {
			throw new DatabaseException(id);
		}
		
	}

	private void updateData(Payment entity, Payment obj) {
		entity.setMoment(obj.getMoment());				
	}
}
