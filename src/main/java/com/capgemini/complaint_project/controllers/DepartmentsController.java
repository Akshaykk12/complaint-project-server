package com.capgemini.complaint_project.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_project.dto.DepartmentsDTO;
import com.capgemini.complaint_project.entities.Department;
import com.capgemini.complaint_project.services.DepartmentsService;

@RestController
@RequestMapping("/api/departments")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class DepartmentsController {
	
	private final DepartmentsService departmentsService;
	
	@Autowired
	public DepartmentsController(DepartmentsService departmentsService) {
		this.departmentsService = departmentsService;
	}

	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments() {
		List<Department> departments = departmentsService.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(departments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
		Department department = departmentsService.getDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(department);
	}

	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department saved = departmentsService.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/departments/" + saved.getDeptId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department newDept) {
		Department updated = departmentsService.updateDepartment(id, newDept);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Department> patchDepartment(@PathVariable Long id, @RequestBody Department patch) {
		Department updated = departmentsService.patchDepartment(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentsService.deleteDepartment(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/deptName")
	public ResponseEntity<List<DepartmentsDTO>> getAllDepartmentDTO() {
		return ResponseEntity.status(HttpStatus.OK).body(departmentsService.getAllDepartmentDTO());
	}
}