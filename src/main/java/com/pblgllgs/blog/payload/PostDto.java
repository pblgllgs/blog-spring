package com.pblgllgs.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@ApiModel(value = "Post model information")
public class PostDto {

    @ApiModelProperty(value = "Post id")
    private Long id;
    @NotBlank
    @Size(min = 2, message = "Post title should have al least 2 characters")
    @ApiModelProperty(value = "Post title")
    private String title;
    @NotBlank
    @Size(min = 10, message = "Post description should have al least 10 characters")
    @ApiModelProperty(value = "Post description")
    private String description;
    @NotBlank
    @Size(min = 10, message = "Post content should have al least 100 characters")
    @ApiModelProperty(value = "Post content")
    private String content;
    @ApiModelProperty(value = "Post comments")
    private Set<CommentDto> comments;
}
