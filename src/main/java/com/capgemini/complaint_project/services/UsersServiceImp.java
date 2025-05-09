package com.capgemini.complaint_project.services;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capgemini.complaint_project.dto.ComplaintData;
import com.capgemini.complaint_project.dto.ComplaintTypeDTO;
import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.dto.UserDTO;
import com.capgemini.complaint_project.entities.User;
import com.capgemini.complaint_project.exception.UserNotFoundException;
import com.capgemini.complaint_project.repositories.UsersRepo;


@Service
public class UsersServiceImp implements UsersService {

	private UsersRepo usersRepo;
	private final String UPLOAD_DIR = "uploads/";
	
	public UsersServiceImp(UsersRepo usersRepo) {
		this.usersRepo = usersRepo;
	}
	
	@Override
	public List<User> getAllUsers() {
		return usersRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return usersRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Employee not found with ID: " + id));
	}

	@Override
	public User createUser(User user) {
		return usersRepo.save(user);
	}

	@Override
	public User updateUser(Long id, User user) {
		User existing = usersRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		existing.setName(user.getName());
		existing.setEmail(user.getEmail());
		existing.setPhone(user.getPhone());
		existing.setUserType(user.getUserType());
		existing.setPassword(user.getPassword());
		return usersRepo.save(existing);
	}

	@Override
	public User patchUser(Long id, User user) {
		User existing = usersRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

		if (user.getName() != null) {
			existing.setName(user.getName());
		}
		if (user.getEmail() != null) {
			existing.setEmail(user.getEmail());
		}
		if (user.getPhone() != null) {
			existing.setPhone(user.getPhone());
		}
		if (user.getUserType() != null) {
			existing.setUserType(user.getUserType());
		}
		if (user.getPassword() != null) {
			existing.setPassword(user.getPassword());
		}
		return usersRepo.save(existing);
	}

	@Override
	public void deleteUser(Long id) {
		if (!usersRepo.existsById(id)) {
			throw new UserNotFoundException("Cannot delete. User not found with ID: \" + id");
		}
		usersRepo.deleteById(id);
	}

	@Override
	public ComplaintData getFormData() {
//		List<DepartmentsDTO> departments = DepartmentsDTO.get
		return null;
	}

	@Override
	public List<UserDTO> getAllUserDTO() {
		return usersRepo.findAll().stream().map(user -> new UserDTO(user.getId(), user.getName())).collect(Collectors.toList());
	}

	@Override
	public User findByEmail(String email) {
		return usersRepo.findByEmail(email);
	}
	@Override
	public int getTotalUser() {
		return usersRepo.findAll().size();
	}
}
