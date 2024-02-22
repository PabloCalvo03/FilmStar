package com.filmstar.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.filmstar.api.entities.Role;
import com.filmstar.api.entities.User;
import com.filmstar.api.repositories.MovieRepository;
import com.filmstar.api.repositories.RatingRepository;
import com.filmstar.api.repositories.UserRepository;

@Component
public class InitializeData implements CommandLineRunner {
	
	// Para borrar las peliculas poner esto en true
	final Boolean borrarPeliculas = false;
	
	// Para borrar los ratings poner eesto en true
	final Boolean borrarRatings = true;

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
        
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	if(borrarPeliculas) movieRepository.deleteAll();
    	if(borrarRatings) ratingRepository.deleteAll();
    	try {
    		
            User user = new User();
            user.setFirstname("Alice");
            user.setLastname("Johnson");
            user.setEmail("alice.johnson@example.com");
            user.setPassword(passwordEncoder.encode("password123"));
            user.getRoles().add(Role.ROLE_USER);
            userRepository.save(user);
            
            User admin = new User();
            admin.setFirstname("Bob");
            admin.setLastname("Smith");
            admin.setEmail("bob.smith@example.com");
            admin.setPassword(passwordEncoder.encode("password456"));
            admin.getRoles().add(Role.ROLE_ADMIN);
            userRepository.save(admin);
            
            
    	}catch(Exception e) {
    		
    	}
    	
        
    }
}
