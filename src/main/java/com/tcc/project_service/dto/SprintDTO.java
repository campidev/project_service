package com.tcc.project_service.dto;

import java.time.LocalDateTime;

public class SprintDTO {
    private Long sprintId;
    private Long projectId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    // 🔹 Getters & Setters
    public Long getSprintId() { return sprintId; }
    public void setSprintId(Long sprintId) { this.sprintId = sprintId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

