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
	
	// Para cargar las peliculas poner esto en false
	final Boolean borrarPeliculas = false;
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
    		
            User User1 = new User();
            User1.setFirstname("Alice");
            User1.setLastname("Johnson");
            User1.setEmail("alice.johnson@example.com");
            User1.setPassword(passwordEncoder.encode("password123"));
            User1.getRoles().add(Role.ROLE_USER);
            userRepository.save(User1);
            
            User User2 = new User();
            User2.setFirstname("Bob");
            User2.setLastname("Smith");
            User2.setEmail("bob.smith@example.com");
            User2.setPassword(passwordEncoder.encode("password456"));
            User2.getRoles().add(Role.ROLE_ADMIN);
            userRepository.save(User2);
            
            
    	}catch(Exception e) {
    		
    	}
    	
        
    }
}
