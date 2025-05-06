package com.capgemini.complaint_project.exception;


public class DepartmentNameAlreadyExistException extends RuntimeException {
	
//	String message;
	
	public DepartmentNameAlreadyExistException(String message) {
		super(message);
	}

}
