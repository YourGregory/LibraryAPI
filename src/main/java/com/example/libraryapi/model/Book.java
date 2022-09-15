package com.example.libraryapi.model;

import com.example.libraryapi.model.commonFields.CommonIdentifierFieldEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "book")
public class Book extends CommonIdentifierFieldEntity {

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    LocalDate releaseDate;

    @Column
    String description;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
}
