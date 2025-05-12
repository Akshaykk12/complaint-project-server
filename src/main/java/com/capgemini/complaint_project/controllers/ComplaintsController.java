package com.capgemini.complaint_project.controllers;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.complaint_project.dto.ComplaintStatusCountDTO;
import com.capgemini.complaint_project.dto.ComplaintsDatesCountDTO;
import com.capgemini.complaint_project.dto.DepartmentComplaintCountDTO;
import com.capgemini.complaint_project.entities.Complaint;
import com.capgemini.complaint_project.services.ComplaintsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/complaints")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ComplaintsController {
	private final ComplaintsService complaintService;

	public ComplaintsController(ComplaintsService complaintService) {
		this.complaintService = complaintService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Complaint>> getAllComplaints(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplaints());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintById(id));
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Complaint> saveComplaints(@RequestParam Long userId, @RequestParam Long deptId, @RequestParam Long ctId, @RequestParam String description, @RequestParam LocalDate date, @RequestParam String status, @RequestParam LocalDate updateDate , @RequestParam("proofImage") MultipartFile proofImage) throws IOException{
		return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.createComplaint(userId, deptId, ctId, description, date, status, updateDate, proofImage));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Complaint> updateComplaints(@PathVariable Long id, @RequestBody Complaint complaint){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.updateComplaint(id, complaint));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComplaint(@PathVariable Long id){
		complaintService.deleteComplaint(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/getCompByDept/{deptName}")
	public ResponseEntity<List<Complaint>> getCompByDept(@PathVariable String deptName){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintByDeptName(deptName));
	}
	
	@GetMapping("/getTotalComp")
	public ResponseEntity<Integer> getTotalComplaints(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getTotalComplaints());
	}
	
	@GetMapping("/getCompStatusCount")
	public ResponseEntity<List<ComplaintStatusCountDTO>> getCompStatusCount(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getCompStatusCount());
	}
	
	@GetMapping("/getCompDateCount")
	public ResponseEntity<List<ComplaintsDatesCountDTO>> getCompDateCount(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getCompDateCount());
	}
	
	@GetMapping("/getDeptCompCount")
	public ResponseEntity<List<DepartmentComplaintCountDTO>> getDeptCompCount(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getDeptCompCount());
	}

	@GetMapping("/getTopDeptCompCount")
	public ResponseEntity<List<DepartmentComplaintCountDTO>> getTop5DeptCompCount(){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getTop5DeptCompCount());
	}
	
	
	@GetMapping("/resource/{filename}")
	public ResponseEntity<Resource> getSymbol(@PathVariable String filename) throws IOException {
		Path filePath = Paths.get("uploads", filename);
		Resource resource = new UrlResource(filePath.toUri());
		if (resource.exists()) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<List<Complaint>> getComplaintByUserId(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.findComplaintByUserId(userId));
	}
	
}
