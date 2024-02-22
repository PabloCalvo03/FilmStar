package com.filmstar.api.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.entities.Movie;
import com.filmstar.api.entities.User;
import com.filmstar.api.exceptions.MovieNotFoundException;
import com.filmstar.api.services.FavoriteMovieService;
import com.filmstar.api.services.MovieService;

@RestController
@RequestMapping("/api/v1/favorites/{movieId}")
public class DoAddMovieToFavoritePostController {
	
	private final FavoriteMovieService favoriteMovieService;
    private final MovieService movieService;

    public DoAddMovieToFavoritePostController(MovieService movieService, FavoriteMovieService favoriteMovieService) {
    	this.movieService = movieService;
		this.favoriteMovieService = favoriteMovieService;
	}

    /**
     * Añade una película a la lista de favoritos para un usuario.
     *
     * @param movieId ID de la película a añadir
     * @param user Usuario autenticado
     * @return ResponseEntity con el mensaje y HttpStatus correspondientes
     */
    @PostMapping
    public ResponseEntity<?> addFavorite(
            @PathVariable Integer movieId,
            @AuthenticationPrincipal User user) {
		try {
	    	Movie movie = movieService.findById(movieId);
	    	if(favoriteMovieService.isMovieInFavorites(user, movie)) {
		        return new ResponseEntity<Map<String, String>>(Map.of("message", "La pelicula ya esta en favoritos."), HttpStatus.CREATED);
	        }
	        favoriteMovieService.addFavoriteMovie(user, movie);
	        return new ResponseEntity<Map<String, String>>(Map.of("message", "Pelicula añadida a favoritos."), HttpStatus.CREATED);
		} catch(MovieNotFoundException e) {
			throw e;
		}
    }
}
