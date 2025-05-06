package com.capgemini.complaint_project.exception;

public class ComplaintNotFoundException extends RuntimeException {
	public ComplaintNotFoundException(String message) {
		super(message);
	}
}
