package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.request.AuthorRequest;
import com.example.libraryapi.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toAuthor(AuthorRequest authorRequest);
}
