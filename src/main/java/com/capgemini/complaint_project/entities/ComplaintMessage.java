package com.capgemini.complaint_project.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "complaint_messages")
public class ComplaintMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String compId;
    
    private String fromWho;
    
    private String toWho;

    private String message;

    private LocalDateTime timestamp;

    // Constructors
    public ComplaintMessage() {}

    public ComplaintMessage(String compId, String message, String fromWho, String toWho) {
        this.compId = compId;
        this.message = message;
        this.fromWho = fromWho;
        this.toWho = toWho;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    // ...
}