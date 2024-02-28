package com.filmstar.api.usecases;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.services.MovieService;

@Component
public class DeleteMovieUseCase {
	
	private final MovieService movieService;
	
	public DeleteMovieUseCase(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public ResponseEntity<Map<String, String>> execute(Integer id){
        movieService.deleteById(id);
        return new ResponseEntity<Map<String, String>>(Map.of("message", "Movie deleted."), HttpStatus.ACCEPTED);
	}

}
