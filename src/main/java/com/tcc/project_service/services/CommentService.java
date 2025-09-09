package com.tcc.project_service.services;

import com.tcc.project_service.dto.CommentDTO;
import com.tcc.project_service.mappers.CommentMapper;
import com.tcc.project_service.models.Comment;
import com.tcc.project_service.repositories.CommentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentDTO> getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDTO);
    }

    public List<CommentDTO> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO dto) {
        Comment comment = commentMapper.toEntity(dto);
        Comment saved = commentRepository.save(comment);
        return commentMapper.toDTO(saved);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

