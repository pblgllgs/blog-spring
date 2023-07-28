package com.pblgllgs.blog.service.impl;

import com.pblgllgs.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long postId);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long commentId, long postId);

    CommentDto updateComment(CommentDto commentDto, long commentId, long postId);

    void deleteComment(long commentId, long postId);
}
