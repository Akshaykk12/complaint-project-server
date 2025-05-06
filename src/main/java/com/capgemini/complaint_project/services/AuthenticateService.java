package com.capgemini.complaint_project.services;

import com.capgemini.complaint_project.entities.User;

public interface AuthenticateService {
	
	User authenticate(String email, String password);
}
