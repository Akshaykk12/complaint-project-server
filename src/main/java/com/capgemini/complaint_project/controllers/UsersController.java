package com.capgemini.complaint_project.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_project.dto.ComplaintData;
import com.capgemini.complaint_project.dto.ComplaintTypeDTO;
import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.dto.UserDTO;
import com.capgemini.complaint_project.entities.User;
import com.capgemini.complaint_project.services.ComplaintTypesService;
import com.capgemini.complaint_project.services.DepartmentsService;
import com.capgemini.complaint_project.services.UsersService;

//@CrossOrigin(origins = "http://127.0.0.1:5501")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private final UsersService usersService;
	private final DepartmentsService departmentService;
	private final ComplaintTypesService complaintTypesService;
	
	public UsersController(UsersService usersService, DepartmentsService departmentService, ComplaintTypesService complaintTypesService) {
		this.usersService = usersService;
		this.departmentService = departmentService;
		this.complaintTypesService = complaintTypesService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = usersService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUsers(@PathVariable Long id) {
		User user = usersService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saved = usersService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users/" + saved.getId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newEmp) {
		User updated = usersService.updateUser(id, newEmp);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User patch) {
		User updated = usersService.patchUser(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		usersService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@GetMapping("/form-data")
	public ResponseEntity<ComplaintData> getFormData(){
		List<DepartmentsDTO> departments = departmentService.getAllDepartmentDTO();
		List<ComplaintTypeDTO> compType = complaintTypesService.getAllComplaintTypeDTO();
		List<UserDTO> users = usersService.getAllUserDTO();
		return ResponseEntity.status(HttpStatus.OK).body(new ComplaintData(departments, compType, users));
	}
	@GetMapping("/getTotalUsers")
	public ResponseEntity<Integer> getTotalUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.getTotalUser());
	}
}
