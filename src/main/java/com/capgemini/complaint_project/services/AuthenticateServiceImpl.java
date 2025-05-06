package com.capgemini.complaint_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_project.entities.User;
import com.capgemini.complaint_project.repositories.UsersRepo;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	
	private final UsersRepo usersRepo;
	
	@Autowired
	public AuthenticateServiceImpl(UsersRepo usersRepo) {
		this.usersRepo = usersRepo;
	}


	@Override
	public User authenticate(String email, String password) {
		// TODO Auto-generated method stub
		User user = usersRepo.findByEmailAndPassword(email, password);
		
		if(user != null) {
			return user;
		}else {
			return null;
		}
	}

}
