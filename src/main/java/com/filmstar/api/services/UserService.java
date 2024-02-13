package com.filmstar.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.filmstar.api.dtos.responses.UserResponse;
import com.filmstar.api.entities.User;

public interface UserService {
    UserDetailsService userDetailsService();
    List<UserResponse> getAllUsers();
	Optional<User> findByUsername(String username);
}