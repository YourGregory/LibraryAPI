package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.request.CreateBookRequest;
import com.example.libraryapi.dto.request.UpdateBookRequest;
import com.example.libraryapi.dto.response.BookResponse;
import com.example.libraryapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    List<BookResponse> toBookResponseList(List<Book> bookList);

    @Mapping(target = "authorFullName", source = "author.fullName")
    BookResponse toBookResponse(Book book);

    Book toBook(CreateBookRequest createBookRequest);

    void updateBook(@MappingTarget Book book, UpdateBookRequest updateBookRequest);
}
