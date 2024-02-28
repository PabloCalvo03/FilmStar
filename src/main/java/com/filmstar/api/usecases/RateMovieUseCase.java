package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.dtos.requests.MovieRatingRequest;
import com.filmstar.api.dtos.responses.MovieRatingResponse;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.services.MovieService;

@Component
public class RateMovieUseCase {

	private final MovieService movieService;
	
	public RateMovieUseCase(MovieService movieService) {
		this.movieService = movieService;
	}

	public ResponseEntity<?> execute(MovieRatingRequest requestBody, User user) {
	        Rating rating = movieService.rateMovie(requestBody.getId(), requestBody.getScore(), user);
	        MovieRatingResponse ratingResponse = new MovieRatingResponse(rating);
	        return new ResponseEntity<>(ratingResponse, HttpStatus.CREATED);
	}
}
