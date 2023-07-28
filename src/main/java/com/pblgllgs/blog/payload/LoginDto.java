package com.pblgllgs.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotBlank
    @Size(min = 4, message = "Name should have al least 2 characters ")
    private String usernameOrEmail;
    @NotBlank
    @Size(min = 4, message = "Password should have al least 2 characters")
    private String password;
}
