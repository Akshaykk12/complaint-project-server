package com.capgemini.complaint_project.controllers;

import java.net.URI;
import java.util.List;

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

import com.capgemini.complaint_project.entities.ComplaintType;
import com.capgemini.complaint_project.services.ComplaintTypesService;


@RestController
@RequestMapping("/api/complaint-types")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ComplaintTypesController {
	
	private final ComplaintTypesService complaintTypesService;
	
	public ComplaintTypesController(ComplaintTypesService complaintTypesService) {
		this.complaintTypesService = complaintTypesService;
	}
	
	@GetMapping
	public ResponseEntity<List<ComplaintType>> getAllComplaintType() {
		List<ComplaintType> compType = complaintTypesService.getAllComplaintType();
		return ResponseEntity.status(HttpStatus.OK).body(compType);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintType> getComplaintType(@PathVariable Long id) {
		ComplaintType compType = complaintTypesService.getComplaintTypeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(compType);
	}

	@PostMapping
	public ResponseEntity<ComplaintType> createComplaintType(@RequestBody ComplaintType compType) {
		ComplaintType saved = complaintTypesService.saveComplaintType(compType);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/complaint-types/" + saved.getCompTypeId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComplaintType> updateComplaintType(@PathVariable Long id, @RequestBody ComplaintType newEmp) {
		ComplaintType updated = complaintTypesService.updateComplaintType(id, newEmp);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ComplaintType> patchComplaintType(@PathVariable Long id, @RequestBody ComplaintType patch) {
		ComplaintType updated = complaintTypesService.patchComplaintType(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComplaintType(@PathVariable Long id) {
		complaintTypesService.deleteComplaintType(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}