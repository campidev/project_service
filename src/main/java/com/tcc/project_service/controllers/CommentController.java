package com.tcc.project_service.controllers;

import com.tcc.project_service.dto.CommentDTO;
import com.tcc.project_service.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con id " + id));
    }

    @GetMapping("/task/{taskId}")
    public List<CommentDTO> getCommentsByTask(@PathVariable Long taskId) {
        return commentService.getCommentsByTask(taskId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentDTO> getCommentsByUser(@PathVariable Long userId) {
        return commentService.getCommentsByUser(userId);
    }

    @PostMapping
    public CommentDTO createComment(@RequestBody CommentDTO dto) {
        return commentService.createComment(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

