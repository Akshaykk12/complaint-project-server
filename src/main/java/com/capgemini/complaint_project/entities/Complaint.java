package com.capgemini.complaint_project.entities;

import java.time.LocalDate;
import java.util.Date;

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
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long compId;
	
	@NotNull(message = "User ID is required")
	@Positive(message= "User ID cannot be negative")
	private Long userId;
	
	@NotNull(message = "Department ID is required")
	@Positive(message= "Department ID cannot be negative")
	private Long deptId;
	
	@NotNull(message = "Complaint Type ID is required")
	@Positive(message= "Complaint Type ID cannot be negative")
	private Long ctId;
	
	@NotBlank(message = "Description is required")
	private String description;
	
//	@NotNull(message = "Date is required")
	private LocalDate date;
	
	@NotBlank(message = "Status is required")
	private String status;
	
	private LocalDate updateDate;
	
	private String proofImage;
	

}