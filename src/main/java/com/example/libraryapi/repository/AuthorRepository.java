package com.example.libraryapi.repository;

import com.example.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Boolean existsByFullName(String fullName);

    @Query("select a.authorId from Author a where a.fullName = ?1")
    Long getAuthorIdByAuthorFullName(String fullName);
}
