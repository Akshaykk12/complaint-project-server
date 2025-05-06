package com.capgemini.complaint_project.services;

import java.util.List;

import com.capgemini.complaint_project.dto.ComplaintTypeDTO;
import com.capgemini.complaint_project.entities.ComplaintType;


public interface ComplaintTypesService {
	List<ComplaintType> getAllComplaintType();

	ComplaintType getComplaintTypeById(Long id);

	ComplaintType saveComplaintType(ComplaintType compType);

	ComplaintType updateComplaintType(Long id, ComplaintType compType);
	
	ComplaintType patchComplaintType(Long id, ComplaintType compType);

	void deleteComplaintType(Long id);
	
	List<ComplaintTypeDTO> getAllComplaintTypeDTO();

}
