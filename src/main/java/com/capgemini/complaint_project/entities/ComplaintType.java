package com.capgemini.complaint_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long compTypeId;
	
	@NotBlank(message = "Complaint Type is required")
	public String compType;
	
	@NotBlank(message = "Severity is required")
	public String severity;
}
