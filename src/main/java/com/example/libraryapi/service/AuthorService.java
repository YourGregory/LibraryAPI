package com.example.libraryapi.service;

import com.example.libraryapi.config.exceptions.ResourceNotFoundException;
import com.example.libraryapi.dto.request.AuthorRequest;
import com.example.libraryapi.mapper.AuthorMapper;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class AuthorService {

    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    @Transactional
    public Author createAuthor(AuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: ", authorId));
    }

    @Transactional
    public Author updateAuthor(Long id, AuthorRequest authorRequest) {
        Author author = findAuthorById(id);
        author = authorMapper.toAuthor(authorRequest);
        return authorRepository.save(author);
    }

    @Transactional
    public Author manageAuthor(AuthorRequest authorRequest) {
        Author author;
        log.info(authorRequest.getFullName());
        if (authorRepository.existsByFullName(authorRequest.getFullName())) {
            log.info("Author exists");
            author = updateAuthor(
                    authorRepository
                            .getAuthorIdByAuthorFullName(authorRequest.getFullName()),
                    authorRequest);
        } else {
            log.info("Author does not exist");
            author = createAuthor(authorRequest);
        }
        return author;
    }
}
