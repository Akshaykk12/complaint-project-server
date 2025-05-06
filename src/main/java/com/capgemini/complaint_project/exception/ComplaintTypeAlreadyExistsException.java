package com.capgemini.complaint_project.exception;

public class ComplaintTypeAlreadyExistsException extends RuntimeException {
	public ComplaintTypeAlreadyExistsException(String msg) {
		super(msg);
	}
}
