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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "author")
public class Author extends CommonIdentifierFieldEntity {

    @Column(nullable = false, unique = true)
    String fullName;

    @Column(nullable = false)
    LocalDate birthday;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    Set<Book> books;
}
