package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;
import com.filmstar.api.util.EntityValidator;

@Component
public class CreateMovieUseCase {
	
	private final MovieService movieService;
	
	public CreateMovieUseCase(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public ResponseEntity<?> execute(Movie movie, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(EntityValidator.execute(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
	}

}
