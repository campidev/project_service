package com.tcc.project_service.mappers;

import com.tcc.project_service.dto.SprintDTO;
import com.tcc.project_service.models.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public SprintDTO toDTO(Sprint sprint) {
        SprintDTO dto = new SprintDTO();
        dto.setSprintId(sprint.getSprintId());
        dto.setProjectId(sprint.getProjectId());
        dto.setName(sprint.getName());
        dto.setStartDate(sprint.getStartDate());
        dto.setEndDate(sprint.getEndDate());
        dto.setStatus(sprint.getStatus());
        return dto;
    }

    public Sprint toEntity(SprintDTO dto) {
        Sprint sprint = new Sprint();
        sprint.setSprintId(dto.getSprintId());
        sprint.setProjectId(dto.getProjectId());
        sprint.setName(dto.getName());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        sprint.setStatus(dto.getStatus());
        return sprint;
    }
}

