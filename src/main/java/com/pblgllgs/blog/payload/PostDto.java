package com.pblgllgs.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {

    private Long id;
    @NotBlank
    @Size(min = 2, message = "Post title should have al least 2 characters")
    private String title;
    @NotBlank
    @Size(min = 10, message = "Post description should have al least 10 characters")
    private String description;
    @NotBlank
    @Size(min = 10, message = "Post content should have al least 100 characters")
    private String content;
    private Set<CommentDto> comments;
}
