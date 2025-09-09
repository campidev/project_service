package com.tcc.project_service.services;

import com.tcc.project_service.dto.CommentDTO;
import com.tcc.project_service.mappers.CommentMapper;
import com.tcc.project_service.models.Comment;
import com.tcc.project_service.repositories.CommentRepository;
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

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private CommentDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        comment = new Comment(1L, 2L, "Comentario de prueba");
        comment.setCommentId(1L);

        dto = new CommentDTO();
        dto.setCommentId(1L);
        dto.setTaskId(1L);
        dto.setUserId(2L);
        dto.setContent("Comentario de prueba");
    }

    @Test
    void testGetAllComments() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment));
        when(commentMapper.toDTO(comment)).thenReturn(dto);

        List<CommentDTO> result = commentService.getAllComments();

        assertEquals(1, result.size());
        assertEquals("Comentario de prueba", result.get(0).getContent());
    }

    @Test
    void testGetCommentById_Found() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentMapper.toDTO(comment)).thenReturn(dto);

        Optional<CommentDTO> result = commentService.getCommentById(1L);

        assertTrue(result.isPresent());
        assertEquals("Comentario de prueba", result.get().getContent());
    }

    @Test
    void testGetCommentById_NotFound() {
        when(commentRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<CommentDTO> result = commentService.getCommentById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateComment() {
        when(commentMapper.toEntity(dto)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.toDTO(comment)).thenReturn(dto);

        CommentDTO saved = commentService.createComment(dto);

        assertNotNull(saved);
        assertEquals("Comentario de prueba", saved.getContent());
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentRepository).deleteById(1L);

        commentService.deleteComment(1L);

        verify(commentRepository, times(1)).deleteById(1L);
    }
}

