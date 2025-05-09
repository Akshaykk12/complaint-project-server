package com.capgemini.complaint_project.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.complaint_project.entities.ComplaintMessage;

public interface ComplaintMessageRepository extends JpaRepository<ComplaintMessage, Long> {
	
    List<ComplaintMessage> findTop20ByCompIdOrderByTimestampDesc(String compId);
}
