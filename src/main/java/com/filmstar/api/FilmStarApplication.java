package com.filmstar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class FilmStarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmStarApplication.class, args);
	}

}
