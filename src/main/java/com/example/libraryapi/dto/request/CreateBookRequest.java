package com.example.libraryapi.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBookRequest {

    @NotBlank(message = "Book title cannot be blank")
    @NotNull(message = "Book title cannot be null")
    String title;

    @NotNull(message = "Release date cannot be null")
    LocalDate releaseDate;

    String description;

    @NotNull(message = "Author ID cannot be null")
    Long authorId;
}
