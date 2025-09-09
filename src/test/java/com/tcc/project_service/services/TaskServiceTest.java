package com.tcc.project_service.services;

import com.tcc.project_service.dto.TaskDTO;
import com.tcc.project_service.mappers.TaskMapper;
import com.tcc.project_service.models.Task;
import com.tcc.project_service.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task = new Task(1L, "Tarea Test", 1L);
        task.setTaskId(1L);

        taskDTO = new TaskDTO();
        taskDTO.setTaskId(1L);
        taskDTO.setProjectId(1L);
        taskDTO.setTitle("Tarea Test");
        taskDTO.setCreatedBy(1L);
    }

    @Test
    void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        List<TaskDTO> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Tarea Test", result.get(0).getTitle());
    }

    @Test
    void testGetTaskById_Found() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        Optional<TaskDTO> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals("Tarea Test", result.get().getTitle());
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<TaskDTO> result = taskService.getTaskById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateTask() {
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO saved = taskService.createTask(taskDTO);

        assertNotNull(saved);
        assertEquals("Tarea Test", saved.getTitle());
    }

    @Test
    void testUpdateTask_Found() {
        TaskDTO updateDTO = new TaskDTO();
        updateDTO.setTitle("Tarea Actualizada");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toDTO(any(Task.class))).thenReturn(updateDTO);

        TaskDTO updated = taskService.updateTask(1L, updateDTO);

        assertEquals("Tarea Actualizada", updated.getTitle());
    }

    @Test
    void testUpdateTask_NotFound() {
        TaskDTO updateDTO = new TaskDTO();
        updateDTO.setTitle("Tarea Inexistente");

        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                taskService.updateTask(99L, updateDTO));

        assertEquals("Tarea no encontrada con id 99", exception.getMessage());
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}

