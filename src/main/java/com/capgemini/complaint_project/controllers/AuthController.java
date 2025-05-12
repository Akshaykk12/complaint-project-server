package com.capgemini.complaint_project.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_project.dto.LoginDto;
import com.capgemini.complaint_project.entities.User;
import com.capgemini.complaint_project.exception.UserNotFoundException;
import com.capgemini.complaint_project.security.JwtUtils;
import com.capgemini.complaint_project.services.UsersService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	AuthenticationManager authenticationManager;
	UsersService userService;
	PasswordEncoder passwordEncoder;
	JwtUtils jwtService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UsersService userService,
			PasswordEncoder passwordEncoder, JwtUtils jwtService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/signin")
	public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		if (authentication.isAuthenticated()) {
			User user = userService.findByNameOrEmail(loginDto.getUsername(), loginDto.getUsername());
			Map<String, Object> claims = new HashMap<>();
			claims.put("email", user.getEmail());
			claims.put("userid", user.getId());
			claims.put("usertype", user.getUserType());
			String token = jwtService.generateToken(loginDto.getUsername(),claims);
			Map<String, String> response = new HashMap<>();
	        response.put("token", token);

	        return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		Map<String, String> error = new HashMap<>();
	    error.put("error", "You are not Authorized !!");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		System.err.println("in register api ");
		if (userService.existsByEmail(user.getName()) || userService.existsByEmail(user.getEmail()))
			throw new UserNotFoundException("Username or Email Exists !");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
	}
}
