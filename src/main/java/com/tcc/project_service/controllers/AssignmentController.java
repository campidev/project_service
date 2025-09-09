package com.tcc.project_service.controllers;

import com.tcc.project_service.dto.AssignmentDTO;
import com.tcc.project_service.services.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public List<AssignmentDTO> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public AssignmentDTO getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Asignación no encontrada con id " + id
                ));
    }

    @GetMapping("/task/{taskId}")
    public List<AssignmentDTO> getAssignmentsByTask(@PathVariable Long taskId) {
        return assignmentService.getAssignmentsByTask(taskId);
    }

    @GetMapping("/user/{userId}")
    public List<AssignmentDTO> getAssignmentsByUser(@PathVariable Long userId) {
        return assignmentService.getAssignmentsByUser(userId);
    }

    @PostMapping
    public AssignmentDTO createAssignment(@RequestBody AssignmentDTO dto) {
        return assignmentService.createAssignment(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }
}

