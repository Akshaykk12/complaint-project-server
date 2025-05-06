package com.capgemini.complaint_project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintData {
	
	private List<DepartmentsDTO> departments;
	private List<ComplaintTypeDTO> complaintTypes;
	private List<UserDTO> users;

}
