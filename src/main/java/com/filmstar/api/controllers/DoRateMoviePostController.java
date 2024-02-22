package com.filmstar.api.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.responses.MovieRatingResponse;
import com.filmstar.api.entities.Rating;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/movies/{id}/rate")
public class DoRateMoviePostController {
	private final MovieService movieService;
	
	public DoRateMoviePostController(MovieService movieService) {
		this.movieService = movieService;
	}

	
	/**
	 * Realiza una calificación para una película.
	 *
	 * @param id    ID de la película a calificar.
	 * @param score Puntuación de la calificación.
	 * @param user  Usuario autenticado que realiza la calificación.
	 * @return Calificación creada o mensaje de error si no se encuentra la película o el usuario no está autenticado.
	 */
	@PostMapping("/{id}/rate")
    public ResponseEntity<?> rateMovie(
            @PathVariable Integer id,
            @RequestParam int score,
            @AuthenticationPrincipal User user) {
        
        try {
            if (user == null) {
                return new ResponseEntity<>(Map.of("error", "Usuario no autenticado."), HttpStatus.UNAUTHORIZED);
            }

            Rating rating = movieService.rateMovie(id, score, user);
            MovieRatingResponse ratingResponse = new MovieRatingResponse(rating);
            return new ResponseEntity<>(ratingResponse, HttpStatus.CREATED);

        } catch (MovieNotFoundException e) {
            throw e;
        }
    }
}
