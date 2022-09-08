package com.example.libraryapi.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 100, message = "Username should have minimum 4 characters, maximum - 100")
    String username;

    @NotBlank(message = "Email cannot be blank")
    @Size(max = 50, message = "Email maximum length is 50 characters")
    @Email(message = "Email should be as example test@post.com")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Size(min = 4, message = "Password should have minimum 4 characters")
    String password;
}
