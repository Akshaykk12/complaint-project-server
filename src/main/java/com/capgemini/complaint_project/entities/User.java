package com.capgemini.complaint_project.entities;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "User Name cannot be Empty")
	private String name;
	
	@NotBlank(message = "User Email cannot be Empty")
	private String email;
	
	@NotNull(message = "Phone number cannot be Empty")
	@Positive(message = "Salary must be positive")
	private BigInteger phone;
	
	@NotBlank(message = "User Type cannot be Empty")
	private String userType;
	
	@NotBlank(message = "Password cannot be Empty")
	private String password;
}