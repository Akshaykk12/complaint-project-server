package com.capgemini.complaint_project.services;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.complaint_project.dto.ComplaintStatusCountDTO;
import com.capgemini.complaint_project.dto.ComplaintsDatesCountDTO;
import com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO;
import com.capgemini.complaint_project.entities.Complaint;
import com.capgemini.complaint_project.exception.ComplaintNotFoundException;
import com.capgemini.complaint_project.exception.UserNotFoundException;
import com.capgemini.complaint_project.repositories.ComplaintsRepo;

@Service
public class ComplaintsServiceImpl implements ComplaintsService {
	
	private final ComplaintsRepo complaintsRepo;
	private final String UPLOAD_DIR = "uploads/";
	
	@Autowired
	public ComplaintsServiceImpl(ComplaintsRepo complaintsRepo) {
		this.complaintsRepo = complaintsRepo;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintsRepo.findAll();
	}

	@Override
	public Complaint getComplaintById(Long id) {
		return complaintsRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Cannot delete. User not found with ID:" + id));
	}

	@Override
	public Complaint createComplaint(Long userId, Long deptId, Long ctId, String description, LocalDate date, String status, LocalDate updateDate, MultipartFile file) throws IOException {
		Files.createDirectories(Paths.get(UPLOAD_DIR));
		// Save the file to the folder
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR, fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		Complaint complaint = new Complaint();
		complaint.setUserId(userId);
		complaint.setDeptId(deptId);
		complaint.setCtId(ctId);
		complaint.setDescription(description);
		complaint.setDate( LocalDate.now());
		complaint.setStatus(status);
		complaint.setUpdateDate(updateDate);
		complaint.setProofImage(fileName);
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
		comp.setUpdateDate(LocalDate.now());
		
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
	
	@Override
	public int getTotalComplaints() {
		return complaintsRepo.findAll().size();
	}
	
	@Override
	public List<ComplaintStatusCountDTO> getCompStatusCount() {
		return complaintsRepo.getComplaintStatusCount();
	}
	
	@Override
	public List<ComplaintsDatesCountDTO> getCompDateCount() {
		return complaintsRepo.getComplaintDateCount();
	}
	
	@Override
	public List<DepartmentComplaintCountDTO> getDeptCompCount() {
		return complaintsRepo.getComplaintCountsByDepartment();
	}
	
	@Override
	public List<DepartmentComplaintCountDTO> getTop5DeptCompCount() {
		PageRequest pageRequest = PageRequest.of(0,5);
		return complaintsRepo.getTopComplaintCountsByDepartment(pageRequest);
	}
	
	@Override
	public String findDescriptionById(Long id) {
		return complaintsRepo.findDescriptionByCompId(id);
	}
	@Override
	public List<Complaint> findComplaintByUserId(Long userId) {
		return complaintsRepo.findComplaintByUserId(userId);
	}
}