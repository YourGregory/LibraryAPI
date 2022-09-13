package com.example.libraryapi.repository;

import com.example.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByFullName(String fullName);

    @Query("select a.id from Author a where a.fullName = :fullName")
    Optional<Long> getAuthorIdByAuthorFullName(@Param("fullName") String fullName);
}
