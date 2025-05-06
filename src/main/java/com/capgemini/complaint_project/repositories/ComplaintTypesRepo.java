package com.capgemini.complaint_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_project.entities.ComplaintType;

@Repository
public interface ComplaintTypesRepo extends JpaRepository<ComplaintType, Long> {

	boolean existsByCompType(String compTypeName);
}
