package com.tcc.project_service.services;

import com.tcc.project_service.dto.ProjectDTO;
import com.tcc.project_service.mappers.ProjectMapper;
import com.tcc.project_service.models.Project;
import com.tcc.project_service.repositories.ProjectRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDTO> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        Project saved = projectRepository.save(project);
        return projectMapper.toDTO(saved);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(projectDTO.getName());
                    project.setDescription(projectDTO.getDescription());
                    project.setStartDate(projectDTO.getStartDate());
                    project.setEndDate(projectDTO.getEndDate());
                    project.setStatus(projectDTO.getStatus());
                    Project updated = projectRepository.save(project);
                    return projectMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id " + id));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}


