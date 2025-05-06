package com.capgemini.complaint_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_project.entities.Department;

@Repository
public interface DepartmentsRepo extends JpaRepository<Department, Long> {
	
	boolean existsByDeptName(String deptName);

}
