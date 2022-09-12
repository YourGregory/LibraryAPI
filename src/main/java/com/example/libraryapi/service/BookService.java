package com.example.libraryapi.service;

import com.example.libraryapi.config.exceptions.ResourceNotFoundException;
import com.example.libraryapi.dto.request.CreateBookRequest;
import com.example.libraryapi.dto.request.UpdateBookRequest;
import com.example.libraryapi.dto.response.BookResponse;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class BookService {

    BookMapper bookMapper;
    BookRepository bookRepository;
    AuthorService authorService;

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookMapper.toBookResponseList(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", bookId));
    }

    @Transactional
    public BookResponse saveBook(CreateBookRequest createBookRequest) {
        Author author = authorService.manageAuthor(createBookRequest.getAuthorRequest());
        Book book = bookMapper.toBook(createBookRequest);
        book.setAuthor(author);
        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    public BookResponse updateBook(Long id, UpdateBookRequest updateBookRequest) {
        Book book = findBookById(id);
        Author author = authorService.findAuthorById(updateBookRequest.getAuthorId());
        book.setAuthor(author);
        return bookMapper.toBookResponse(bookRepository.save(bookMapper.updateBook(book, updateBookRequest)));
    }
}
