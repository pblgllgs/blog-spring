package com.pblgllgs.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private Long id;

    @NotBlank
    @Size(min = 2, message = "Comment name should have al least 2 characters")
    private String name;

    @NotBlank(message = "Comment email shouldn't be empty")
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, message = "Comment body should have al least 10 characters")
    private String body;
}
