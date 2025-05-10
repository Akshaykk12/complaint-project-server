package com.capgemini.complaint_project.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.complaint_project.entities.Complaint;
import com.capgemini.complaint_project.services.ComplaintsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/complaints")
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
	
	@PostMapping
	public ResponseEntity<Complaint> saveComplaints(@RequestBody Complaint complaint){
		return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.createComplaint(complaint));
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

}
