package com.tcc.project_service.controllers;


import com.tcc.project_service.dto.TaskDTO;
import com.tcc.project_service.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
    }

    @GetMapping("/project/{projectId}")
    public List<TaskDTO> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    @GetMapping("/sprint/{sprintId}")
    public List<TaskDTO> getTasksBySprint(@PathVariable Long sprintId) {
        return taskService.getTasksBySprint(sprintId);
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

