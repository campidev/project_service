package com.tcc.project_service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROJECTS")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "CLOB")
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 30)
    private String status = "EN_PROGRESO";

    @Column(nullable = false)
    private Long createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    // 🔹 Constructores
    public Project() {}

    public Project(String name, String description, Long createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    // 🔹 Getters y setters
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

