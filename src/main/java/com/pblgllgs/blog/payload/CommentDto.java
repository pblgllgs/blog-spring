package com.pblgllgs.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "CommentDto model information", description = "This a class DTO")
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private Long id;

    @ApiModelProperty(value = "Comment name")
    @NotBlank
    @Size(min = 2, message = "Comment name should have al least 2 characters")
    private String name;

    @ApiModelProperty(value = "Comment email user")
    @NotBlank(message = "Comment email shouldn't be empty")
    @Email
    private String email;

    @ApiModelProperty(value = "Comment message in post")
    @NotBlank
    @Size(min = 10, message = "Comment body should have al least 10 characters")
    private String body;
}
