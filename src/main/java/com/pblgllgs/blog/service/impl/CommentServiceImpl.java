package com.pblgllgs.blog.service.impl;

import com.pblgllgs.blog.entity.Comment;
import com.pblgllgs.blog.entity.Post;
import com.pblgllgs.blog.exception.BlogAPIException;
import com.pblgllgs.blog.exception.ResourceNotFoundException;
import com.pblgllgs.blog.payload.CommentDto;
import com.pblgllgs.blog.repository.CommentRepository;
import com.pblgllgs.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Comment comment = mapToEntity(commentDto);
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId)));
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentServiceImpl::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long commentId, long postId) {
        Comment comment = getComment(commentId, postId);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long commentId, long postId) {
        Comment comment = getComment(commentId, postId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        commentRepository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public void deleteComment(long commentId, long postId) {
        Comment comment = getComment(commentId, postId);
        commentRepository.deleteById(comment.getId());
    }

    public static CommentDto mapToDto(Comment comment) {
        CommentDto commentResponse = new CommentDto();
        commentResponse.setId(comment.getId());
        commentResponse.setName(comment.getName());
        commentResponse.setEmail(comment.getEmail());
        commentResponse.setBody(comment.getBody());
        return commentResponse;
    }

    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    private Comment getComment(long commentId, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "The comment does not belong to post");
        }
        return comment;
    }
}
