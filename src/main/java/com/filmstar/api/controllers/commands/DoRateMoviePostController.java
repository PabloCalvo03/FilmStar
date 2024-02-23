package com.filmstar.api.controllers.commands;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.MovieRatingRequest;
import com.filmstar.api.dtos.responses.MovieRatingResponse;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movies/rate")
public class DoRateMoviePostController {
	private final MovieService movieService;
	
	public DoRateMoviePostController(MovieService movieService) {
		this.movieService = movieService;
	}

	
	/**
	 * Realiza una calificación para una película.
	 *
	 * @param requestBody Contiene la información necesaria para realizar la calificación, como el id de la película y la puntuación.
	 * @param user        Usuario autenticado que realiza la calificación.
	 * @return Calificación creada o mensaje de error si no se encuentra la película o el usuario no está autenticado.
	 */
	@PostMapping
	public ResponseEntity<?> rateMovie(
	        @RequestBody MovieRatingRequest requestBody,
	        @AuthenticationPrincipal User user) {

	        if (user == null) {
	            return new ResponseEntity<>(Map.of("error", "Usuario no autenticado."), HttpStatus.UNAUTHORIZED);
	        }

	        Rating rating = movieService.rateMovie(requestBody.getId(), requestBody.getScore(), user);
	        MovieRatingResponse ratingResponse = new MovieRatingResponse(rating);
	        return new ResponseEntity<>(ratingResponse, HttpStatus.CREATED);

	}
}
