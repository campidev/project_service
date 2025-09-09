package com.tcc.project_service.controllers;

import com.tcc.project_service.dto.SprintDTO;
import com.tcc.project_service.services.SprintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping
    public List<SprintDTO> getAllSprints() {
        return sprintService.getAllSprints();
    }

    @GetMapping("/{id}")
    public SprintDTO getSprintById(@PathVariable Long id) {
        return sprintService.getSprintById(id)
                .orElseThrow(() -> new RuntimeException("Sprint no encontrado con id " + id));
    }

    @GetMapping("/project/{projectId}")
    public List<SprintDTO> getSprintsByProject(@PathVariable Long projectId) {
        return sprintService.getSprintsByProject(projectId);
    }

    @PostMapping
    public SprintDTO createSprint(@RequestBody SprintDTO sprintDTO) {
        return sprintService.createSprint(sprintDTO);
    }

    @PutMapping("/{id}")
    public SprintDTO updateSprint(@PathVariable Long id, @RequestBody SprintDTO sprintDTO) {
        return sprintService.updateSprint(id, sprintDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
    }
}

