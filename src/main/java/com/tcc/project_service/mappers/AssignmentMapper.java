package com.tcc.project_service.mappers;

import com.tcc.project_service.dto.AssignmentDTO;
import com.tcc.project_service.models.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public AssignmentDTO toDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setAssignmentId(assignment.getAssignmentId());
        dto.setTaskId(assignment.getTaskId());
        dto.setUserId(assignment.getUserId());
        dto.setRole(assignment.getRole());
        dto.setAssignedAt(assignment.getAssignedAt());
        return dto;
    }

    public Assignment toEntity(AssignmentDTO dto) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(dto.getAssignmentId());
        assignment.setTaskId(dto.getTaskId());
        assignment.setUserId(dto.getUserId());
        assignment.setRole(dto.getRole());
        assignment.setAssignedAt(dto.getAssignedAt());
        return assignment;
    }
}

