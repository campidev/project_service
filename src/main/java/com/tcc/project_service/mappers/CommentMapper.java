package com.tcc.project_service.mappers;

import com.tcc.project_service.dto.CommentDTO;
import com.tcc.project_service.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(comment.getCommentId());
        dto.setTaskId(comment.getTaskId());
        dto.setUserId(comment.getUserId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }

    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setCommentId(dto.getCommentId());
        comment.setTaskId(dto.getTaskId());
        comment.setUserId(dto.getUserId());
        comment.setContent(dto.getContent());
        comment.setCreatedAt(dto.getCreatedAt());
        return comment;
    }
}
