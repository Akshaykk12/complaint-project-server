package com.capgemini.complaint_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintStatusCountDTO {
	
	String status;
	long count;
}