package com.capgemini.complaint_project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintsDatesCountDTO {
	
	LocalDate date;
	Long count;
	

}
