package com.tcc.project_service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private Long projectId;

    private Long sprintId;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "CLOB")
    private String description;

    @Column(length = 20)
    private String priority = "MEDIA"; // BAJA, MEDIA, ALTA, URGENTE

    @Column(length = 30)
    private String status = "PENDIENTE"; // PENDIENTE, EN_PROGRESO, COMPLETADA

    private LocalDateTime dueDate;

    @Column(nullable = false)
    private Long createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    // 🔹 Constructores
    public Task() {}

    public Task(Long projectId, String title, Long createdBy) {
        this.projectId = projectId;
        this.title = title;
        this.createdBy = createdBy;
    }

    // 🔹 Getters & Setters
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Long getSprintId() { return sprintId; }
    public void setSprintId(Long sprintId) { this.sprintId = sprintId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

