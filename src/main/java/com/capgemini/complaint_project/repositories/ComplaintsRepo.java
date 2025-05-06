package com.capgemini.complaint_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_project.entities.Complaint;

@Repository
public interface ComplaintsRepo extends JpaRepository<Complaint, Long> {

	@Query("Select c,d from Complaint c JOIN Department d  on c.deptId = d.deptId where d.deptName = ?1 ")
	List<Complaint> findComplaintByDeptName(String deptName);
}
