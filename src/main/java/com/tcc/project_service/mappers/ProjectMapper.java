package com.tcc.project_service.mappers;

import com.tcc.project_service.dto.ProjectDTO;
import com.tcc.project_service.models.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(project.getProjectId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setStatus(project.getStatus());
        dto.setCreatedBy(project.getCreatedBy());
        dto.setCreatedAt(project.getCreatedAt());
        return dto;
    }

    public Project toEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setProjectId(dto.getProjectId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setStatus(dto.getStatus());
        project.setCreatedBy(dto.getCreatedBy());
        project.setCreatedAt(dto.getCreatedAt());
        return project;
    }
}

