package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.request.RegisterRequest;
import com.example.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RegisterRequest request);
}
