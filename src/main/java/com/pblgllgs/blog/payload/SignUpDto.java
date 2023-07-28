package com.pblgllgs.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {
    @NotBlank
    @Size(min = 2, message = "Name should have al least 2 characters ")
    private String name;

    @NotBlank
    @Size(min = 4, message = "Name should have al least 2 characters ")
    private String username;
    @Email
    private String email;
    @NotBlank
    @Size(min = 4, message = "Name should have al least 2 characters ")
    private String password;
}