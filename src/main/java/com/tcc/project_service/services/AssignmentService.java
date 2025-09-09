package com.tcc.project_service.services;

import com.tcc.project_service.dto.AssignmentDTO;
import com.tcc.project_service.mappers.AssignmentMapper;
import com.tcc.project_service.models.Assignment;
import com.tcc.project_service.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssignmentDTO> getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .map(assignmentMapper::toDTO);
    }

    public List<AssignmentDTO> getAssignmentsByTask(Long taskId) {
        return assignmentRepository.findByTaskId(taskId)
                .stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AssignmentDTO> getAssignmentsByUser(Long userId) {
        return assignmentRepository.findByUserId(userId)
                .stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AssignmentDTO createAssignment(AssignmentDTO dto) {
        Assignment assignment = assignmentMapper.toEntity(dto);
        Assignment saved = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(saved);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}

