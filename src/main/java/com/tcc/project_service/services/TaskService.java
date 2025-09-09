package com.tcc.project_service.services;


import com.tcc.project_service.dto.TaskDTO;
import com.tcc.project_service.mappers.TaskMapper;
import com.tcc.project_service.models.Task;
import com.tcc.project_service.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDTO);
    }

    public List<TaskDTO> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksBySprint(Long sprintId) {
        return taskRepository.findBySprintId(sprintId)
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task saved = taskRepository.save(task);
        return taskMapper.toDTO(saved);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDTO.getTitle());
                    task.setDescription(taskDTO.getDescription());
                    task.setPriority(taskDTO.getPriority());
                    task.setStatus(taskDTO.getStatus());
                    task.setDueDate(taskDTO.getDueDate());
                    task.setSprintId(taskDTO.getSprintId());
                    Task updated = taskRepository.save(task);
                    return taskMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id " + id));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

