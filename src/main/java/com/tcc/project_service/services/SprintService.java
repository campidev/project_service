package com.tcc.project_service.services;


import com.tcc.project_service.dto.SprintDTO;
import com.tcc.project_service.mappers.SprintMapper;
import com.tcc.project_service.models.Sprint;
import com.tcc.project_service.repositories.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;

    public SprintService(SprintRepository sprintRepository, SprintMapper sprintMapper) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
    }

    public List<SprintDTO> getAllSprints() {
        return sprintRepository.findAll()
                .stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SprintDTO> getSprintById(Long id) {
        return sprintRepository.findById(id)
                .map(sprintMapper::toDTO);
    }

    public List<SprintDTO> getSprintsByProject(Long projectId) {
        return sprintRepository.findByProjectId(projectId)
                .stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SprintDTO createSprint(SprintDTO sprintDTO) {
        Sprint sprint = sprintMapper.toEntity(sprintDTO);
        Sprint saved = sprintRepository.save(sprint);
        return sprintMapper.toDTO(saved);
    }

    public SprintDTO updateSprint(Long id, SprintDTO sprintDTO) {
        return sprintRepository.findById(id)
                .map(sprint -> {
                    sprint.setName(sprintDTO.getName());
                    sprint.setStartDate(sprintDTO.getStartDate());
                    sprint.setEndDate(sprintDTO.getEndDate());
                    sprint.setStatus(sprintDTO.getStatus());
                    Sprint updated = sprintRepository.save(sprint);
                    return sprintMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Sprint no encontrado con id " + id));
    }

    public void deleteSprint(Long id) {
        sprintRepository.deleteById(id);
    }
}

