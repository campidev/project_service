package com.tcc.project_service.services;

import com.tcc.project_service.dto.SprintDTO;
import com.tcc.project_service.mappers.SprintMapper;
import com.tcc.project_service.models.Sprint;
import com.tcc.project_service.repositories.SprintRepository;
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

class SprintServiceTest {

    @Mock
    private SprintRepository sprintRepository;

    @Mock
    private SprintMapper sprintMapper;

    @InjectMocks
    private SprintService sprintService;

    private Sprint sprint;
    private SprintDTO sprintDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sprint = new Sprint(1L, "Sprint Test");
        sprint.setSprintId(1L);

        sprintDTO = new SprintDTO();
        sprintDTO.setSprintId(1L);
        sprintDTO.setProjectId(1L);
        sprintDTO.setName("Sprint Test");
    }

    @Test
    void testGetAllSprints() {
        when(sprintRepository.findAll()).thenReturn(Arrays.asList(sprint));
        when(sprintMapper.toDTO(sprint)).thenReturn(sprintDTO);

        List<SprintDTO> result = sprintService.getAllSprints();

        assertEquals(1, result.size());
        assertEquals("Sprint Test", result.get(0).getName());
        verify(sprintRepository, times(1)).findAll();
    }

    @Test
    void testGetSprintById_Found() {
        when(sprintRepository.findById(1L)).thenReturn(Optional.of(sprint));
        when(sprintMapper.toDTO(sprint)).thenReturn(sprintDTO);

        Optional<SprintDTO> result = sprintService.getSprintById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sprint Test", result.get().getName());
    }

    @Test
    void testGetSprintById_NotFound() {
        when(sprintRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<SprintDTO> result = sprintService.getSprintById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateSprint() {
        when(sprintMapper.toEntity(sprintDTO)).thenReturn(sprint);
        when(sprintRepository.save(sprint)).thenReturn(sprint);
        when(sprintMapper.toDTO(sprint)).thenReturn(sprintDTO);

        SprintDTO saved = sprintService.createSprint(sprintDTO);

        assertNotNull(saved);
        assertEquals("Sprint Test", saved.getName());
    }

    @Test
    void testUpdateSprint_Found() {
        SprintDTO updateDTO = new SprintDTO();
        updateDTO.setName("Sprint Actualizado");

        when(sprintRepository.findById(1L)).thenReturn(Optional.of(sprint));
        when(sprintRepository.save(any(Sprint.class))).thenReturn(sprint);
        when(sprintMapper.toDTO(any(Sprint.class))).thenReturn(updateDTO);

        SprintDTO updated = sprintService.updateSprint(1L, updateDTO);

        assertEquals("Sprint Actualizado", updated.getName());
    }

    @Test
    void testUpdateSprint_NotFound() {
        SprintDTO updateDTO = new SprintDTO();
        updateDTO.setName("Sprint Inexistente");

        when(sprintRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                sprintService.updateSprint(99L, updateDTO));

        assertEquals("Sprint no encontrado con id 99", exception.getMessage());
    }

    @Test
    void testDeleteSprint() {
        doNothing().when(sprintRepository).deleteById(1L);

        sprintService.deleteSprint(1L);

        verify(sprintRepository, times(1)).deleteById(1L);
    }
}

