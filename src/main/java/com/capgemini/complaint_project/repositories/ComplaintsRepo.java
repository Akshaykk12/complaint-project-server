package com.capgemini.complaint_project.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_project.dto.ComplaintStatusCountDTO;
import com.capgemini.complaint_project.dto.ComplaintsDatesCountDTO;
import com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO;
import com.capgemini.complaint_project.entities.Complaint;

@Repository
public interface ComplaintsRepo extends JpaRepository<Complaint, Long> {

	@Query("Select c,d from Complaint c JOIN Department d  on c.deptId = d.deptId where d.deptName = ?1 ")
	List<Complaint> findComplaintByDeptName(String deptName);
	
	
	@Query("SELECT new com.capgemini.complaint_project.dto.ComplaintStatusCountDTO(c.status, COUNT(c)) FROM Complaint c GROUP BY c.status")
	List<ComplaintStatusCountDTO> getComplaintStatusCount();
	
	@Query("SELECT new com.capgemini.complaint_project.dto.ComplaintsDatesCountDTO(c.date, COUNT(c)) FROM Complaint c GROUP BY c.date")
	List<ComplaintsDatesCountDTO> getComplaintDateCount();
	
	@Query("SELECT new com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO(d.deptName, COUNT(c)) FROM Complaint c JOIN Department d ON c.deptId = d.deptId GROUP BY d.deptName")
	List<DepartmentComplaintCountDTO> getComplaintCountsByDepartment();

	@Query("SELECT new com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO(d.deptName, COUNT(c)) FROM Complaint c JOIN Department d ON c.deptId = d.deptId GROUP BY d.deptName ORDER BY COUNT(c) DESC")
	List<DepartmentComplaintCountDTO> getTopComplaintCountsByDepartment(PageRequest pageRequest);
	
	Complaint findComplaintByCompId(Long id);
	
//	Complaint findComplaintByCompId(Long compId);
	
	List<Complaint> findComplaintByUserId(Long id);


	String findDescriptionByCompId(Long id);
}
