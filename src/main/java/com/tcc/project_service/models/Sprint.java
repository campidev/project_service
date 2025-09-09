package com.tcc.project_service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SPRINTS",schema = "SYS")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false, length = 100)
    private String name;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 30)
    private String status = "PLANIFICADO";

    // 🔹 Constructores
    public Sprint() {}

    public Sprint(Long projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

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

