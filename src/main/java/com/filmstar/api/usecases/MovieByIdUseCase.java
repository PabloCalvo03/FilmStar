package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;

@Component
public class MovieByIdUseCase {
	
	private final MovieService movieService;
	
	public MovieByIdUseCase(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public ResponseEntity<Movie> execute(Long id){
		return new ResponseEntity<Movie>(movieService.findById(id), HttpStatus.ACCEPTED);
	}

}
