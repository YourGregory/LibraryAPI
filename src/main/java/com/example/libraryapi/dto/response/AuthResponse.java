package com.example.libraryapi.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    Boolean success;
    String message;

    public static AuthResponse usernameAlreadyTakenResponse() {
        return new AuthResponse(false, RESPONSE.USERNAME_ALREADY_TAKEN.getMessage());
    }

    public static AuthResponse emailAlreadyTakenResponse() {
        return new AuthResponse(false, RESPONSE.EMAIL_ALREADY_TAKEN.getMessage());
    }

    public static AuthResponse successfulRegisterResponse() {
        return new AuthResponse(true, RESPONSE.SUCCESSFUL_REGISTER.getMessage());
    }

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    enum RESPONSE {

        USERNAME_ALREADY_TAKEN("Username  is already taken!"),
        EMAIL_ALREADY_TAKEN("Email  is already taken!"),
        SUCCESSFUL_REGISTER("User registered successfully!");

        String message;
    }
}
