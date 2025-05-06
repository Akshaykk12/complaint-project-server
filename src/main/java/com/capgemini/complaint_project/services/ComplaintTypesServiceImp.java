package com.capgemini.complaint_project.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_project.dto.ComplaintTypeDTO;
import com.capgemini.complaint_project.entities.ComplaintType;
import com.capgemini.complaint_project.exception.ComplaintTypeAlreadyExistsException;
import com.capgemini.complaint_project.exception.ComplaintTypeNotFoundException;
import com.capgemini.complaint_project.repositories.ComplaintTypesRepo;

@Service
public class ComplaintTypesServiceImp implements ComplaintTypesService {

	ComplaintTypesRepo complaintTypeRepo;

	@Autowired
	public ComplaintTypesServiceImp(ComplaintTypesRepo complaintTypeRepo) {
		super();
		this.complaintTypeRepo = complaintTypeRepo;
	}

	@Override
	public List<ComplaintType> getAllComplaintType() {
		// TODO Auto-generated method stub
		return complaintTypeRepo.findAll();
	}

	@Override
	public ComplaintType getComplaintTypeById(Long id) {
		// TODO Auto-generated method stub
		return complaintTypeRepo.findById(id)
				.orElseThrow(() -> new ComplaintTypeNotFoundException("Complaint Type not found with ID: " + id));
	}

	@Override
	public ComplaintType saveComplaintType(ComplaintType compType) {
		if(complaintTypeRepo.existsByCompType(compType.getCompType())) {
			throw new ComplaintTypeAlreadyExistsException("Complaint Type Already Exists");
		}
		return complaintTypeRepo.save(compType);

	}

	@Override
	public ComplaintType updateComplaintType(Long id, ComplaintType complaintType) {
		// TODO Auto-generated method stub
		ComplaintType complaintType1 = getComplaintTypeById(id);
		complaintType1.setCompType(complaintType.getCompType());
		complaintType1.setSeverity(complaintType.getSeverity());
		return complaintTypeRepo.save(complaintType1);
	}

	@Override
	public void deleteComplaintType(Long id) {
		// TODO Auto-generated method stub
		if (!complaintTypeRepo.existsById(id)) {
			throw new ComplaintTypeNotFoundException("Complaint Type not found with ID: " + id);
		}
		complaintTypeRepo.deleteById(id);
	}

	@Override
	public ComplaintType patchComplaintType(Long id, ComplaintType compType) {
		// TODO Auto-generated method stub
		ComplaintType existing = getComplaintTypeById(id);

		if (compType.getCompType() != null) {
			existing.setCompType(compType.getCompType());
		}
		if (compType.getSeverity() != null) {
			existing.setSeverity(compType.getSeverity());
		}
		return complaintTypeRepo.save(existing);
	}

	@Override
	public List<ComplaintTypeDTO> getAllComplaintTypeDTO() {
		// TODO Auto-generated method stub
		return complaintTypeRepo.findAll().stream().map(comp -> new ComplaintTypeDTO(comp.getCompTypeId(), comp.getCompType())).collect(Collectors.toList());
	}
}
