package com.tcc.project_service.services;

import com.tcc.project_service.dto.AssignmentDTO;
import com.tcc.project_service.mappers.AssignmentMapper;
import com.tcc.project_service.models.Assignment;
import com.tcc.project_service.repositories.AssignmentRepository;
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

class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private AssignmentMapper assignmentMapper;

    @InjectMocks
    private AssignmentService assignmentService;

    private Assignment assignment;
    private AssignmentDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        assignment = new Assignment(1L, 2L, "DESARROLLADOR");
        assignment.setAssignmentId(1L);

        dto = new AssignmentDTO();
        dto.setAssignmentId(1L);
        dto.setTaskId(1L);
        dto.setUserId(2L);
        dto.setRole("DESARROLLADOR");
    }

    @Test
    void testGetAllAssignments() {
        when(assignmentRepository.findAll()).thenReturn(Arrays.asList(assignment));
        when(assignmentMapper.toDTO(assignment)).thenReturn(dto);

        List<AssignmentDTO> result = assignmentService.getAllAssignments();

        assertEquals(1, result.size());
        assertEquals("DESARROLLADOR", result.get(0).getRole());
    }

    @Test
    void testGetAssignmentById_Found() {
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        when(assignmentMapper.toDTO(assignment)).thenReturn(dto);

        Optional<AssignmentDTO> result = assignmentService.getAssignmentById(1L);

        assertTrue(result.isPresent());
        assertEquals("DESARROLLADOR", result.get().getRole());
    }

    @Test
    void testGetAssignmentById_NotFound() {
        when(assignmentRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<AssignmentDTO> result = assignmentService.getAssignmentById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateAssignment() {
        when(assignmentMapper.toEntity(dto)).thenReturn(assignment);
        when(assignmentRepository.save(assignment)).thenReturn(assignment);
        when(assignmentMapper.toDTO(assignment)).thenReturn(dto);

        AssignmentDTO saved = assignmentService.createAssignment(dto);

        assertNotNull(saved);
        assertEquals("DESARROLLADOR", saved.getRole());
    }

    @Test
    void testDeleteAssignment() {
        doNothing().when(assignmentRepository).deleteById(1L);

        assignmentService.deleteAssignment(1L);

        verify(assignmentRepository, times(1)).deleteById(1L);
    }
}

