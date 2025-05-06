package com.capgemini.complaint_project.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_project.entities.User;
import com.capgemini.complaint_project.services.AuthenticateService;
import com.capgemini.complaint_project.services.UsersService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthenticateController {
	
	private final AuthenticateService authService;
	private final UsersService userService;

	@Autowired
	public AuthenticateController(AuthenticateService authService, UsersService userService) {
		this.authService = authService;
		this.userService = userService;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticate(@RequestBody Map<String, String> body) {
	    String email = body.get("email");
	    String password = body.get("password");

//	    return ResponseEntity.status(HttpStatus.OK).body( authService.authenticate(email, password));
	    User user = authService.authenticate(email, password);

	    return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/checkEmail")
	public ResponseEntity<String> registerUser(@RequestBody User newUser) {
//	    Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
	    User existingUser = userService.findByEmail(newUser.getEmail());

	    if (existingUser != null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Email already registered");
	    }

	    userService.createUser(newUser);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body("User registered successfully");
	}
	

}
