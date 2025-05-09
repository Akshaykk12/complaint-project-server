package com.capgemini.complaint_project.services;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.complaint_project.dto.ComplaintData;
import com.capgemini.complaint_project.dto.ComplaintTypeDTO;
import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.dto.UserDTO;
import com.capgemini.complaint_project.entities.User;


public interface UsersService {
	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, User user);

	User patchUser(Long id, User user);

	void deleteUser(Long id);
	
	ComplaintData getFormData();
	
	List<UserDTO> getAllUserDTO();
	
	User findByEmail(String email);
	
	int getTotalUser();
}
