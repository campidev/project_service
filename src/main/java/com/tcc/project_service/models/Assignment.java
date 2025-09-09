package com.tcc.project_service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ASSIGNMENTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"taskId", "userId"})
})
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 50)
    private String role;

    private LocalDateTime assignedAt = LocalDateTime.now();

    // 🔹 Constructores
    public Assignment() {}

    public Assignment(Long taskId, Long userId, String role) {
        this.taskId = taskId;
        this.userId = userId;
        this.role = role;
    }

    // 🔹 Getters & Setters
    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}

