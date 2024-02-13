package com.filmstar.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filmstar.api.dtos.responses.UserResponse;
import com.filmstar.api.entities.User;
import com.filmstar.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
	@Override
	public List<UserResponse> getAllUsers() {
		List<UserResponse> allUsers =  userRepository.findAll().stream()
			    .map(usuario -> new UserResponse(usuario.getFirstname(), usuario.getLastname(), usuario.getEmail(), usuario.getRoles().toString()))
			    .collect(Collectors.toList());
		 return allUsers;
	}
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByEmail(username);
	}
}