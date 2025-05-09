package com.capgemini.complaint_project.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.capgemini.complaint_project.dto.ComplaintStatusCountDTO;
import com.capgemini.complaint_project.dto.ComplaintsDatesCountDTO;
import com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO;
import com.capgemini.complaint_project.entities.Complaint;

public interface ComplaintsService {
	
	List<Complaint> getAllComplaints();
	
	Complaint getComplaintById(Long id);
	
	Complaint createComplaint(Long userId, Long deptId, Long ctId, String description, LocalDate date, String status, LocalDate updateDate, MultipartFile file) throws IOException;
	
	Complaint updateComplaint(Long id, Complaint complaint);
	
//	Complaints patchComplaint()
	
	void deleteComplaint(Long id);
	
	List<Complaint> getComplaintByDeptName(String deptName);
	
	int getTotalComplaints();
	
	List<ComplaintStatusCountDTO> getCompStatusCount();
	
	List<ComplaintsDatesCountDTO> getCompDateCount();
	
	List<DepartmentComplaintCountDTO> getDeptCompCount();
	
	List<DepartmentComplaintCountDTO> getTop5DeptCompCount();
	
	Complaint findDescriptionById(Long id);

}
