package com.pblgllgs.blog.controller;

import com.pblgllgs.blog.payload.CommentDto;
import com.pblgllgs.blog.service.impl.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findAllCommentsById(@PathVariable("postId") long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> findCommentIdAndPostId(@PathVariable("postId") long postId, @PathVariable("id") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(commentId, postId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId,
            @PathVariable("id") long commentId
    ) {
        return new ResponseEntity<>(commentService.updateComment(commentDto, commentId, postId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId, @PathVariable("id") long commentId) {
        commentService.deleteComment(commentId, postId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
