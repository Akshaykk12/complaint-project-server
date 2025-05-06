package com.capgemini.complaint_project.services;

import java.util.List;

import com.capgemini.complaint_project.entities.Complaint;

public interface ComplaintsService {
	
	List<Complaint> getAllComplaints();
	
	Complaint getComplaintById(Long id);
	
	Complaint createComplaint(Complaint complaint);
	
	Complaint updateComplaint(Long id, Complaint complaint);
	
//	Complaints patchComplaint()
	
	void deleteComplaint(Long id);
	
	List<Complaint> getComplaintByDeptName(String deptName);

}
