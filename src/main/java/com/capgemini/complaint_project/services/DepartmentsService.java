package com.capgemini.complaint_project.services;

import java.util.List;

import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.entities.Department;


public interface DepartmentsService {
	List<Department> getAllDepartments();

	Department getDepartmentById(Long id);

	Department saveDepartment(Department department);

	Department updateDepartment(Long id, Department department);
	
	Department patchDepartment(Long id, Department department);

	void deleteDepartment(Long id);
	
	List<DepartmentsDTO> getAllDepartmentDTO();

}
