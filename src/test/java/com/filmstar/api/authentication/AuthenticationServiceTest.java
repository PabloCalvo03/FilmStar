package com.filmstar.api.authentication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.filmstar.api.dtos.requests.SignUpRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.entities.Role;
import com.filmstar.api.entities.User;
import com.filmstar.api.repositories.UserRepository;
import com.filmstar.api.services.AuthenticationService;
import com.filmstar.api.services.AuthenticationServiceImpl;
import com.filmstar.api.services.JwtService;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest("John", "Doe", "john.doe@example.com", "password");
        when(userRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(jwtService.generateToken(any(User.class))).thenReturn("fakeJwtToken");

        JwtAuthenticationResponse response = authenticationService.signup(signUpRequest);

        assertNotNull(response);
        assertNotNull(response.getToken());
        
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
    }

    @Test
    void testSignupWithEmailAlreadyInUse() {
        SignUpRequest signUpRequest = new SignUpRequest("John", "Doe", "john.doe@example.com", "password");
        when(userRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.signup(signUpRequest));

        verify(userRepository, never()).save(any(User.class));
        verify(jwtService, never()).generateToken(any(User.class));
    }
}
