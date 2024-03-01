package com.filmstar.api.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.dtos.responses.MovieListPaginatedResponse;
import com.filmstar.api.entities.Movie;
import com.filmstar.api.services.MovieService;

@Component
public class ListMovieUseCase {
	
	private final MovieService movieService;
	
	public ListMovieUseCase(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public ResponseEntity<MovieListPaginatedResponse> execute(Pageable pageable){
		Page<Movie> movies = movieService.findAll(pageable);
		MovieListPaginatedResponse response = new MovieListPaginatedResponse(movies.getTotalElements(), movies.getTotalPages(), movies.getNumber(), movies.getContent());
		return new ResponseEntity<MovieListPaginatedResponse>(response, HttpStatus.OK);
	}
}
