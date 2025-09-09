package com.tcc.project_service.mappers;

import com.tcc.project_service.dto.TaskDTO;
import com.tcc.project_service.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(task.getTaskId());
        dto.setProjectId(task.getProjectId());
        dto.setSprintId(task.getSprintId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedBy(task.getCreatedBy());
        dto.setCreatedAt(task.getCreatedAt());
        return dto;
    }

    public Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTaskId(dto.getTaskId());
        task.setProjectId(dto.getProjectId());
        task.setSprintId(dto.getSprintId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        task.setCreatedBy(dto.getCreatedBy());
        task.setCreatedAt(dto.getCreatedAt());
        return task;
    }
}

