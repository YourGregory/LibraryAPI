package com.example.libraryapi.service;

import com.example.libraryapi.config.PropertiesConfig;
import com.example.libraryapi.dictionary.Role;
import com.example.libraryapi.dto.request.LoginRequest;
import com.example.libraryapi.dto.request.RegisterRequest;
import com.example.libraryapi.dto.security.JwtAuthenticationResponse;
import com.example.libraryapi.dto.security.UserPrincipal;
import com.example.libraryapi.mapper.UserMapper;
import com.example.libraryapi.model.User;
import com.example.libraryapi.repository.AuthRepository;
import com.example.libraryapi.dto.response.AuthResponse;
import com.example.libraryapi.security.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    AuthRepository authRepository;
    UserMapper userMapper;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;
    PropertiesConfig.JwtProperties properties;

    @Transactional
    public AuthResponse registerUser(@Valid RegisterRequest request) {

        log.info(request.getUsername());
        Optional<AuthResponse> credentialsAreUniqueResponse = credentialsAreUnique(request);

        if (credentialsAreUniqueResponse.isPresent()) {
            return credentialsAreUniqueResponse.get();
        }

        User user = userMapper.toUser(request);
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        authRepository.save(user);
        return AuthResponse.successfulRegisterResponse();
    }

    @Transactional(readOnly = true)
    public JwtAuthenticationResponse loginUser(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Long userId = userPrincipal.getId();

            String accessToken = tokenProvider.getAccessToken(authentication);
            String refreshToken = tokenProvider.getRefreshToken(authentication);

            log.info(accessToken);
            log.info(refreshToken);

            return new JwtAuthenticationResponse(accessToken, refreshToken, userId, "");
        } catch (Exception e) {
            log.info(e.getMessage());
            return new JwtAuthenticationResponse(null, null, null, e.getMessage());
        }
    }

    private Optional<AuthResponse> credentialsAreUnique(RegisterRequest request) {
        if (existsByEmail(request.getEmail())) {
            return Optional.of(AuthResponse.emailAlreadyTakenResponse());
        } else if (existsByUsername(request.getUsername())) {
            return Optional.of(AuthResponse.usernameAlreadyTakenResponse());
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public Boolean existsByUsername(String name) {
        return authRepository.existsByUsername(name);
    }

    @Transactional(readOnly = true)
    public Boolean existsByEmail(String email) {
        return authRepository.existsByEmail(email);
    }
}
