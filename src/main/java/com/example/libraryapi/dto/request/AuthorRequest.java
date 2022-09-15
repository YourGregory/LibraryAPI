package com.example.libraryapi.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequest {

    @NotBlank(message = "Author's full name cannot be blank")
    @NotNull(message = "Author's full name cannot be null")
    String fullName;

    @NotNull(message = "Author's birthday cannot be null")
    LocalDate birthday;
}
