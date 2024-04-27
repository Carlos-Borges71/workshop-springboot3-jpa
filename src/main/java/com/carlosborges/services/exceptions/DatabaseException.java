package com.carlosborges.services.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	 
	public DatabaseException(Object id) {
		super("Could not execute statement, Referential integrity constraint violation. " + id );
	}

}
