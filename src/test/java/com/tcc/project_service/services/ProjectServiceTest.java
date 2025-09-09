package com.tcc.project_service.services;



import com.tcc.project_service.dto.ProjectDTO;
import com.tcc.project_service.mappers.ProjectMapper;
import com.tcc.project_service.models.Project;
import com.tcc.project_service.repositories.ProjectRepository;
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

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        project = new Project("Proyecto Test", "Descripción de prueba", 1L);
        project.setProjectId(1L);

        projectDTO = new ProjectDTO();
        projectDTO.setProjectId(1L);
        projectDTO.setName("Proyecto Test");
        projectDTO.setDescription("Descripción de prueba");
        projectDTO.setCreatedBy(1L);
    }

    @Test
    void testGetAllProjects() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project));
        when(projectMapper.toDTO(project)).thenReturn(projectDTO);

        List<ProjectDTO> result = projectService.getAllProjects();

        assertEquals(1, result.size());
        assertEquals("Proyecto Test", result.get(0).getName());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById_Found() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectMapper.toDTO(project)).thenReturn(projectDTO);

        Optional<ProjectDTO> result = projectService.getProjectById(1L);

        assertTrue(result.isPresent());
        assertEquals("Proyecto Test", result.get().getName());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProjectById_NotFound() {
        when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ProjectDTO> result = projectService.getProjectById(99L);

        assertFalse(result.isPresent());
        verify(projectRepository, times(1)).findById(99L);
    }

    @Test
    void testCreateProject() {
        when(projectMapper.toEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        when(projectMapper.toDTO(project)).thenReturn(projectDTO);

        ProjectDTO saved = projectService.createProject(projectDTO);

        assertNotNull(saved);
        assertEquals("Proyecto Test", saved.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProject_Found() {
        ProjectDTO updateDTO = new ProjectDTO();
        updateDTO.setName("Proyecto Actualizado");
        updateDTO.setDescription("Nueva descripción");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        when(projectMapper.toDTO(any(Project.class))).thenReturn(updateDTO);

        ProjectDTO updated = projectService.updateProject(1L, updateDTO);

        assertEquals("Proyecto Actualizado", updated.getName());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProject_NotFound() {
        ProjectDTO updateDTO = new ProjectDTO();
        updateDTO.setName("Proyecto Inexistente");

        when(projectRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                projectService.updateProject(99L, updateDTO));

        assertEquals("Proyecto no encontrado con id 99", exception.getMessage());
        verify(projectRepository, times(1)).findById(99L);
    }

    @Test
    void testDeleteProject() {
        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).deleteById(1L);
    }
}

