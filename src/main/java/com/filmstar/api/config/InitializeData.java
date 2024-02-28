package com.filmstar.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitializeData implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(InitializeData.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		logger.info("Normal user: " + passwordEncoder.encode("password123"));	
		logger.info("Admin user: " + passwordEncoder.encode("password456"));	
	}

}
