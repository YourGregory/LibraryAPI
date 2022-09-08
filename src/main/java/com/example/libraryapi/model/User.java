package com.example.libraryapi.model;

import com.example.libraryapi.dictionary.Role;
import com.example.libraryapi.model.commonFields.CommonIdentifierFieldEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends CommonIdentifierFieldEntity {

    @Column(nullable = false, length = 100, unique = true)
    String username;

    @Column(nullable = false, length = 50, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    Role role;
}
