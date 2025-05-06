package com.capgemini.complaint_project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.entities.Department;
import com.capgemini.complaint_project.exception.DepartmentNameAlreadyExistException;
import com.capgemini.complaint_project.exception.DepartmentNotFoundException;
import com.capgemini.complaint_project.repositories.DepartmentsRepo;

@Service
public class DepartmentsServiceImp implements DepartmentsService {

	DepartmentsRepo departmentsRepo;
	
	
	@Autowired
	public DepartmentsServiceImp(DepartmentsRepo departmentsRepo) {
		this.departmentsRepo = departmentsRepo;
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentsRepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentsRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));
	}

	@Override
	public Department saveDepartment(Department department) {
//		Department dept = departmentsRepo.findByDeptName(department.getDeptName());
		if(departmentsRepo.existsByDeptName(department.getDeptName())) {
			throw new DepartmentNameAlreadyExistException("Department by the name " + department.getDeptName() + " already exists!");
		}
		return departmentsRepo.save(department);
	}

	@Override
	public Department updateDepartment(Long id, Department department) {
		Department department1 = departmentsRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));
		department1.setDeptName(department.getDeptName());
		department1.setDeptEmail(department.getDeptEmail());
		return departmentsRepo.save(department1);
	}

	@Override
	public void deleteDepartment(Long id) {
		if (!departmentsRepo.existsById(id)) {
			throw new DepartmentNotFoundException("Department not found with ID: " + id);
		}
		departmentsRepo.deleteById(id);
	}

	@Override
	public Department patchDepartment(Long id, Department department) {
		Department existing = departmentsRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));
		
		if(department.getDeptName() != null) {
			existing.setDeptName(department.getDeptName());
		}
		if(department.getDeptEmail() != null) {
			existing.setDeptEmail(department.getDeptEmail());
		}
		return departmentsRepo.save(existing);
	}

	@Override
	public List<DepartmentsDTO> getAllDepartmentDTO() {
		return departmentsRepo.findAll().stream().map(dept -> new DepartmentsDTO(dept.getDeptId(), dept.getDeptName())).collect(Collectors.toList());
	}
}
