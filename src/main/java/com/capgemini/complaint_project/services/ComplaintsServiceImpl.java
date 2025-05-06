package com.capgemini.complaint_project.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_project.entities.Complaint;
import com.capgemini.complaint_project.exception.ComplaintNotFoundException;
import com.capgemini.complaint_project.exception.UserNotFoundException;
import com.capgemini.complaint_project.repositories.ComplaintsRepo;

@Service
public class ComplaintsServiceImpl implements ComplaintsService {
	
	private final ComplaintsRepo complaintsRepo;

	@Autowired
	public ComplaintsServiceImpl(ComplaintsRepo complaintsRepo) {
		this.complaintsRepo = complaintsRepo;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		// TODO Auto-generated method stub
		return complaintsRepo.findAll();
	}

	@Override
	public Complaint getComplaintById(Long id) {
		// TODO Auto-generated method stub
		return complaintsRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Cannot delete. User not found with ID: \" + id"));
	}

	@Override
	public Complaint createComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		complaint.setDate( LocalDate.now());
		return complaintsRepo.save(complaint);
	}

	@Override
	public Complaint updateComplaint(Long id, Complaint complaint) {
		// TODO Auto-generated method stub
		Complaint comp = complaintsRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Cannot delete. User not found with ID: \" + id"));
		comp.setUserId(complaint.getUserId());
		comp.setCtId(complaint.getUserId());
		comp.setDeptId(complaint.getDeptId());
		comp.setDate(complaint.getDate());
		comp.setDescription(complaint.getDescription());
		comp.setStatus(complaint.getStatus());
		
		return complaintsRepo.save(comp);
	}

	@Override
	public void deleteComplaint(Long id) {
		if (!complaintsRepo.existsById(id)) {
			throw new ComplaintNotFoundException("Cannot delete. User not found with ID: " + id);
		}
		complaintsRepo.deleteById(id);
	}

	@Override
	public List<Complaint> getComplaintByDeptName(String deptName) {
		return complaintsRepo.findComplaintByDeptName(deptName);
	}
	

}
